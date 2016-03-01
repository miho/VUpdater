/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.vupdater.core;

import com.google.protobuf.util.JsonFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public final class Repository {

    private final List<Entry> entries = new ArrayList<>();
    private final List<Entry> entriesUnmodifiable
            = Collections.unmodifiableList(entries);
    private final Map<String, Entry> entryMap = new HashMap<>();

    private final List<Delta> deltas = new ArrayList<>();
    private final List<Delta> deltasUnmodifiable
            = Collections.unmodifiableList(deltas);
    private final Map<String, Delta> deltaMap = new HashMap<>();

    private String description = "";

    public Repository() {
    }

    public Repository(Repository other) {

        this.description = other.description;

        other.entries.stream().map(e -> new Entry(e)).
                collect(Collectors.toCollection(() -> this.entries));
        other.deltas.stream().map(d -> new Delta(d)).
                collect(Collectors.toCollection(() -> this.deltas));

        entries.forEach(e -> entryMap.put(e.getId(), e));
        deltas.forEach(d -> deltaMap.put(d.getFrom() + "->" + d.getTo(), d));
    }

    public List<Entry> getEntries() {
        return entriesUnmodifiable;
    }

    public void addEntry(Entry e) {
        entries.add(e);
        entryMap.put(e.getId(), e);
    }

    public boolean removeEntry(Entry e) {
        entries.remove(e);
        return entryMap.remove(e.getId()) != null;
    }

    public boolean removeEntry(String id) {

        Entry e = entryMap.remove(id);

        if (e == null) {
            return false;
        }

        return entries.remove(e);
    }

    public Entry getEntry(int i) {
        return entries.get(i);
    }

    public boolean hasEntry(String id) {
        return entryMap.containsKey(id);
    }

    public List<Delta> getDeltas() {
        return deltasUnmodifiable;
    }

    public void addDelta(Delta d) {
        deltas.add(d);
        deltaMap.put(d.getFrom() + "->" + d.getTo(), d);
    }

    public boolean removeDelta(Delta d) {
        deltas.remove(d);
        return deltaMap.remove(d.getFrom() + "->" + d.getTo()) != null;
    }

    public boolean removeDelta(String from, String to) {

        Delta d = deltaMap.remove(from + "->" + to);

        if (d == null) {
            return false;
        }

        return deltas.remove(d);
    }

    public Delta getDelta(int i) {
        return deltas.get(i);
    }

    public Delta getDelta(String from, String to) {
        return deltaMap.get(from + "->" + to);
    }

    public boolean hasDelta(String from, String to) {
        return deltaMap.containsKey(from + "->" + to);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    private eu.mihosoft.vrl.vupdater.proto.Repository toProto() {
        eu.mihosoft.vrl.vupdater.proto.Repository.Builder builder
                = eu.mihosoft.vrl.vupdater.proto.Repository.newBuilder();

        builder.setDesc(getDescription());

        entries.forEach(e -> builder.addEntry(e.toProto()));
        deltas.forEach(d -> builder.addDelta(d.toProto()));

        return builder.build();
    }

    private static Repository fromProto(Repository result,
            eu.mihosoft.vrl.vupdater.proto.Repository r) {

        result.entries.clear();
        result.deltas.clear();
        result.entryMap.clear();
        result.deltaMap.clear();

        result.setDescription(r.getDesc());

        r.getEntryList().stream().map(e -> Entry.fromProto(e)).
                collect(Collectors.toCollection(() -> result.entries));

        r.getDeltaList().stream().map(d -> Delta.fromProto(d)).
                collect(Collectors.toCollection(() -> result.deltas));

        result.entries.forEach(e -> result.entryMap.put(e.getId(), e));
        result.deltas.forEach(d -> result.deltaMap.put(
                d.getFrom() + "->" + d.getTo(), d));

        return result;
    }

    public void save(OutputStream o, Format format) throws IOException {
        eu.mihosoft.vrl.vupdater.proto.Repository r = toProto();

        if (null != format) {
            switch (format) {
                case BINARY:
                    r.writeTo(o);
                    break;
                case JSON:
                    try (OutputStreamWriter outWriter = new OutputStreamWriter(o)) {
                        JsonFormat.printer().appendTo(r, outWriter);
                    }
                    break;
                default:
                    throw new RuntimeException("Format " + format + " not supported!");
            }
        }

        fromProto(this, r);

    }

    public void save(File f, Format format) throws IOException {

        try (FileOutputStream fo = new FileOutputStream(f)) {
            save(fo, format);
        }
    }

    public void load(InputStream i, Format format) throws IOException {
        eu.mihosoft.vrl.vupdater.proto.Repository r = null;

        switch (format) {
            case BINARY:
                r = eu.mihosoft.vrl.vupdater.proto.Repository.parseFrom(i);
                break;
            case JSON:
                eu.mihosoft.vrl.vupdater.proto.Repository.Builder builder
                        = eu.mihosoft.vrl.vupdater.proto.Repository.newBuilder();
                JsonFormat.parser().merge(new InputStreamReader(i), builder);
                r = builder.build();
                break;
            default:
                throw new IOException("Format " + format + " not supported!");
        }

        fromProto(this, r);
    }

    public void load(File f, Format format) throws IOException {

        try (InputStream i = new FileInputStream(f)) {
            load(i, format);
        }
    }

    public void load(File f) throws IOException {
        try (InputStream i = new FileInputStream(f)) {
            load(i, Format.BINARY);
        } catch (IOException exB) {
            try (InputStream i = new FileInputStream(f)) {
                load(i, Format.JSON);
            } catch (IOException exJ) {
                throw new IOException("Cannot detect file format", exJ);
            }
        }
    }

    @Override
    public String toString() {
        return toProto().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Repository other = (Repository) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.entries, other.entries)) {
            return false;
        }
        if (!Objects.equals(this.deltas, other.deltas)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.entries);
        hash = 37 * hash + Objects.hashCode(this.deltas);
        hash = 37 * hash + Objects.hashCode(this.description);
        return hash;
    }

    public Optional<Delta> findDeltaUpdateFor(String id) {
        return findUpdateFor(id).map(e -> getDelta(id, e.getId()));
    }

    public Optional<Entry> findUpdateFor(String id) {

        // choose the minimum version
        // (we are careful and don't support direct updates)
        VersionInfo min = null;
        Entry minE = null;

        for (Entry e : searchForPossibleUpdates(id)) {

            VersionInfo vInfo = new VersionInfo(e.getVersion());

            if (min == null) {
                min = vInfo;
                minE = e;
            }

            boolean lessThan = vInfo.compareTo(min) < 0;
            boolean equal = vInfo.compareTo(min) == 0;

            // check whether less or equal + shorter, i.e.,
            // 0.1.2.0 and 0.1.2
            // the compare method compares only the min number of digits, in
            // this case 0.1.2
            if (lessThan
                    || (equal && vInfo.getVersion().length()
                    < min.getVersion().length())) {
                min = vInfo;
                minE = e;
            }
        }

        return Optional.ofNullable(minE);
    }

    private List<Entry> searchForPossibleUpdates(String id) {

        Entry currentVersion = new Entry(id, "", "", "");

        PluginIdentifier pId = new PluginIdentifier(
                currentVersion.getName(),
                currentVersion.getVersion());

        List<Entry> updates
                = new ArrayList<>();

        // search for possible updates
        for (Entry e : getEntries()) {

            if (!e.getName().trim().equals(pId.getName())) {
                continue;
            }

            VersionInfo vInfo = new VersionInfo(e.getVersion());

            boolean bigger = vInfo.compareTo(pId.getVersion()) > 0;
            boolean equal = vInfo.compareTo(pId.getVersion()) == 0;

            // check whether bigger or equal + longer, i.e.,
            // 0.1.2.0 and 0.1.2
            // the compare method compares only the min number of digits, in
            // this case 0.1.2
            if (bigger
                    || (equal && vInfo.getVersion().length()
                    > pId.getVersion().getVersion().length())) {
                updates.add(e);
                System.out.println(
                        " --> update = "
                        + e.getName() + "-" + e.getVersion());
            }
        }

        return updates;
    }

}

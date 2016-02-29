/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.vupdater.core;

import java.util.Objects;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public final class Entry {

    private final String id;
    private final String desc;
    private final String path;
    private final String sha1;
    private final String asc;
    private final PluginIdentifier pId;

    public Entry(String id, String desc, String path, String sha1, String asc) {
        this.id = id;
        this.pId = verifyId(id);
        this.desc = desc;
        this.path = path;
        this.sha1 = sha1;
        this.asc = asc;
    }

    public Entry(Entry other) {
        this(other.id, other.desc, other.path, other.sha1, other.asc);
    }

    public Entry(String id, String desc, String path, String sha1) {
        this(id, desc, path, sha1, "");
    }

    public Entry(String id, String desc, String path) {
        this(id, desc, path, "");
    }

    public String getName() {
        return pId.getName();
    }

    public String getVersion() {
        return pId.getVersion().getVersion();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the sha1
     */
    public String getSha1() {
        return sha1;
    }

    /**
     * @return the asc
     */
    public String getAsc() {
        return asc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
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
        final Entry other = (Entry) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    eu.mihosoft.vrl.vupdater.proto.Entry toProto() {
        return eu.mihosoft.vrl.vupdater.proto.Entry.newBuilder().
                setId(getId()).
                setDesc(getDesc()).
                setPath(getPath()).
                setSha1(getSha1()).
                setAsc(getAsc()).build();
    }

    static Entry fromProto(eu.mihosoft.vrl.vupdater.proto.Entry e) {
        return new Entry(e.getId(), e.getDesc(), e.getPath(), e.getSha1(), e.getAsc());
    }

    @Override
    public String toString() {
        return toProto().toString();
    }

    private PluginIdentifier verifyId(String id) {

        String[] parts = getId().split("@");
        
        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Specified id is invalid: '" 
                            + id + "'. Does not contain '@' separator.");
        }
        
        return new PluginIdentifier(parts[0], parts[1]);

    }
}

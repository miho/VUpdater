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
    private final long size;
    private final String fullName;
    private final PluginIdentifier pId;

    public Entry(String id, String fullName, String desc, String path, long size, String sha1) {
        this.id = id;
        this.pId = verifyId(id);
        this.desc = desc;
        this.path = path;
        this.sha1 = sha1;
        this.fullName = fullName;
        this.size = size;
    }

    public Entry(Entry other) {
        this(other.id, other.fullName, other.desc, other.path, other.size, other.sha1);
    }

    public Entry(String id, String fullName, String desc, String path, long size) {
        this(id, fullName, desc, path, size, "");
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
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
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
        
        return Objects.equals(this.id, other.id);
    }

    eu.mihosoft.vrl.vupdater.proto.Entry toProto() {
        return eu.mihosoft.vrl.vupdater.proto.Entry.newBuilder().
                setId(getId()).
                setName(getFullName()).
                setDesc(getDesc()).
                setPath(getPath()).
                setSize(getSize()).
                setSha1(getSha1()).
                build();
    }

    static Entry fromProto(eu.mihosoft.vrl.vupdater.proto.Entry e) {
        return new Entry(e.getId(), e.getName(), e.getDesc(), e.getPath(), e.getSize(), e.getSha1());
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

    /**
     * @return the size in bytes
     */
    public long getSize() {
        return size;
    }
}

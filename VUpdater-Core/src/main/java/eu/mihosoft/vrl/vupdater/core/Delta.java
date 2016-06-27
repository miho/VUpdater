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
public final class Delta {

    private final String from;
    private final String to;
    private final String path;
    private final long size;
    private final String sha1;

    public Delta(Delta other) {
        this(other.from, other.to, other.path, other.size, other.sha1);
    }

    public Delta(String from, String to, String path, long size, String sha1) {
        this.from = from;
        this.to = to;
        this.path = path;
        this.size = size;
        this.sha1 = sha1;
    }

    public Delta(String from, String to, String path, long size) {
        this.from = from;
        this.to = to;
        this.path = path;
        this.size = size;
        this.sha1 = "";
    }


    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.from);
        hash = 61 * hash + Objects.hashCode(this.to);
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
        final Delta other = (Delta) obj;
        if (!Objects.equals(this.from, other.from)) {
            return false;
        }
        if (!Objects.equals(this.to, other.to)) {
            return false;
        }
        return true;
    }

    eu.mihosoft.vrl.vupdater.proto.Delta toProto() {
        return eu.mihosoft.vrl.vupdater.proto.Delta.newBuilder().
                setFrom(getFrom()).
                setTo(getTo()).
                setPath(getPath()).
                setSize(getSize()).
                setSha1(getSha1()).
                build();
    }

    static Delta fromProto(eu.mihosoft.vrl.vupdater.proto.Delta d) {
        return new Delta(d.getFrom(), d.getTo(),
                d.getPath(), d.getSize(), d.getSha1());
    }

    @Override
    public String toString() {
        return toProto().toString();
    }

    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }

}

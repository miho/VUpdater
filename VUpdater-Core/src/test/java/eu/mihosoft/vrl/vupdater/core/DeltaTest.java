/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.vupdater.core;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class DeltaTest {

    public DeltaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testClone() {

        // only equal test, since delta is immutable
        Delta instance = new Delta("pkg-abc@1.2.0", "pkg-abc@1.3.0", "http://nopath/delta.zip", 1024L);
        Delta other = new Delta(instance);

        Assert.assertEquals(instance, other);
    }

    /**
     * Test of getFrom method, of class Delta.
     */
    @Test
    public void testGetFrom() {
        Delta instance = new Delta("pkg-abc@1.2.0", "pkg-abc@1.3.0", "http://nopath/delta.zip", 1024L);
        String expResult = "pkg-abc@1.2.0";
        String result = instance.getFrom();
        assertEquals(expResult, result);

    }

    /**
     * Test of getTo method, of class Delta.
     */
    @Test
    public void testGetTo() {
        Delta instance = new Delta("pkg-abc@1.2.0", "pkg-abc@1.3.0", "http://nopath/delta.zip", 1024L);
        String expResult = "pkg-abc@1.3.0";
        String result = instance.getTo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPath method, of class Delta.
     */
    @Test
    public void testGetPath() {
        Delta instance = new Delta("pkg-abc@1.2.0", "pkg-abc@1.3.0", "http://nopath/delta.zip", 1024L);
        String expResult = "http://nopath/delta.zip";
        String result = instance.getPath();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSha1 method, of class Delta.
     */
    @Test
    public void testGetSha1() {
        Delta instance = new Delta("pkg-abc@1.2.0", "pkg-abc@1.3.0",
                "http://nopath/delta.zip", 1024L,
                "4b8fd1b2b9318cf989634e15af0e460a3bc3791a");
        String expResult = "4b8fd1b2b9318cf989634e15af0e460a3bc3791a";
        String result = instance.getSha1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSha1 method, of class Delta.
     */
    @Test
    public void testGetSize() {
        Delta instance = new Delta("pkg-abc@1.2.0", "pkg-abc@1.3.0",
                "http://nopath/delta.zip", 1024L);

        long expResult = 1024;
        long result = instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Delta.
     */
    @Test
    public void testHashCode() {
        // ignore method for now
    }

    /**
     * Test of equals method, of class Delta.
     */
    @Test
    public void testEquals() {
        Delta d1 = new Delta("pkg-abc@1.2.0", "pkg-abc@1.3.0", "http://nopath/delta.zip", 1024L);
        Delta d2 = new Delta("pkg-abc@1.2.0", "pkg-abc@1.3.0", "http://nopath/delta.zipa", 1024L);
        assertEquals(d1, d2);

        Delta d3 = new Delta("pkg-abc@1.2.0", "pkg-abc@1.3.0", "http://nopath/delta.zip", 1024L);
        Delta d4 = new Delta("pkg-abc@1.2.1", "pkg-abc@1.3.0", "http://nopath/delta.zip", 1024L);
        assertEquals(d3.equals(d4), false);

        Delta d5 = new Delta("pkg-abc@1.2.0", "pkg-abc@1.3.1", "http://nopath/delta.zip", 1024L);
        Delta d6 = new Delta("pkg-abc@1.2.0", "pkg-abc@1.3.0", "http://nopath/delta.zip", 1024L);
        assertEquals(d5.equals(d6), false);

        EqualsVerifier.forClass(Delta.class).verify();
    }

    /**
     * Test of toProto method, of class Delta.
     */
    @Test
    public void testToProto() {

        String from = "pkg-abc@1.2.0";
        String to = "pkg-abc@1.3.0";
        String path = "http://nopath/delta.zip";
        String sha1 = "4b8fd1b2b9318cf989634e15af0e460a3bc3791a";
        String asc = "http://nopath/delta.zip.asc";

        Delta d1 = new Delta(from, to, path, 1024L, sha1);

        eu.mihosoft.vrl.vupdater.proto.Delta expResult
                = eu.mihosoft.vrl.vupdater.proto.Delta.newBuilder().
                setFrom(from).
                setTo(to).
                setPath(path).
                setSha1(sha1).
                build();

        assertEquals(expResult, d1.toProto());
    }

    /**
     * Test of fromProto method, of class Delta.
     */
    @Test
    public void testFromProto() {

        String from = "pkg-abc@1.2.0";
        String to = "pkg-abc@1.3.0";
        String path = "http://nopath/delta.zip";
        long size = 1024L;
        String sha1 = "4b8fd1b2b9318cf989634e15af0e460a3bc3791a";

        Delta expResult = new Delta(from, to, path, size, sha1);
        eu.mihosoft.vrl.vupdater.proto.Delta protoRes
                = eu.mihosoft.vrl.vupdater.proto.Delta.newBuilder().
                setFrom(from).
                setTo(to).
                setPath(path).
                setSize(size).
                setSha1(sha1).
                build();
        Delta result = Delta.fromProto(protoRes);

        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class Delta.
     */
    @Test
    public void testToString() {
        // ignore method for now
    }

}

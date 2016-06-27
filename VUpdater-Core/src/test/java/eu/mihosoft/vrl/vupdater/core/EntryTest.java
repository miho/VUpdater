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
public class EntryTest {

    public EntryTest() {
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

        // only equal test, since entry is immutable
        Entry instance = new Entry("pkg-abc@1.2.0", "Package ABC", "Descr...", "http://nopath/delta.zip", 0L);
        Entry other = new Entry(instance);

        Assert.assertEquals(instance, other);
    }

    /**
     * Test of getFullName method, of class Entry.
     */
    @Test
    public void testGetFullName() {

        String id = "my-package@1.2.3";
        String name = "My Package";
        String desc = "my description...";
        String path = "http://nopath/mypackage.zip";

        Entry instance = new Entry(id, name, desc, path, 0L);
        String expResult = "My Package";
        String result = instance.getFullName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Entry.
     */
    @Test
    public void testGetName() {

        String id = "my-package@1.2.3";
        String name = "My Package";
        String desc = "my description...";
        String path = "http://nopath/mypackage.zip";

        Entry instance = new Entry(id, name, desc, path, 0L);
        String expResult = "my-package";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVersion method, of class Entry.
     */
    @Test
    public void testGetVersion() {
        String id = "my-package@1.2.3";
        String name = "My Package";
        String desc = "my description...";
        String path = "http://nopath/mypackage.zip";

        Entry instance = new Entry(id, name, desc, path, 0L);
        String expResult = "1.2.3";
        String result = instance.getVersion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Entry.
     */
    @Test
    public void testGetId() {
        String id = "my-package@1.2.3";
        String name = "My Package";
        String desc = "my description...";
        String path = "http://nopath/mypackage.zip";

        Entry instance = new Entry(id, name, desc, path, 0L);
        String expResult = id;
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDesc method, of class Entry.
     */
    @Test
    public void testGetDesc() {
        String id = "my-package@1.2.3";
        String name = "My Package";
        String desc = "my description...";
        String path = "http://nopath/mypackage.zip";

        Entry instance = new Entry(id, name, desc, path, 0L);
        String expResult = desc;
        String result = instance.getDesc();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPath method, of class Entry.
     */
    @Test
    public void testGetPath() {
        String id = "my-package@1.2.3";
        String name = "My Package";
        String desc = "my description...";
        String path = "http://nopath/mypackage.zip";

        Entry instance = new Entry(id, name, desc, path, 0L);

        String expResult = path;
        String result = instance.getPath();
        assertEquals(expResult, result);
    }
    
        /**
     * Test of getSha1 method, of class Entry.
     */
    @Test
    public void testGetSize() {
        String id = "my-package@1.2.3";
        String name = "My Package";
        String desc = "my description...";
        String path = "http://nopath/mypackage.zip";
        long size   = 1024L;

        Entry instance = new Entry(id, name, desc, path, size);
        long expResult = size;
        long result = instance.getSize();
        assertEquals(expResult, result);

    }

    /**
     * Test of getSha1 method, of class Entry.
     */
    @Test
    public void testGetSha1() {
        String id = "my-package@1.2.3";
        String name = "My Package";
        String desc = "my description...";
        String path = "http://nopath/mypackage.zip";
        String sha1 = "4b8fd1b2b9318cf989634e15af0e460a3bc3791a";

        Entry instance = new Entry(id, name, desc, path, 0L, sha1);
        String expResult = sha1;
        String result = instance.getSha1();
        assertEquals(expResult, result);

    }

    /**
     * Test of hashCode method, of class Entry.
     */
    @Test
    public void testHashCode() {
        // too lazy :(
    }

    /**
     * Test of equals method, of class Entry.
     */
    @Test
    public void testEquals() {

        Entry e1 = new Entry(
                "my-id@1.2.3", "my full name", "my desc",
                "http://path/pkg.zip", 0L,
                "4b8fd1b2b9318cf989634e15af0e460a3bc3791a");

        Entry e2 = new Entry(
                "my-id@1.2.3", "my full name 1", "my desc 1",
                "http://path/pkg.zip", 0L,
                "4b8fd1b2b9318cf989634e15af0e460a3bc3791a");

        assertEquals(e1, e2);

        Entry e3 = new Entry(
                "my-id@1.2.3", "my full name", "my desc",
                "http://path/pkg.zip", 0L,
                "4b8fd1b2b9318cf989634e15af0e460a3bc3791a");

        Entry e4 = new Entry(
                "my-id@1.2.4", "my full name", "my desc",
                "http://path/pkg.abc.zip", 0L,
                "4b8fd1b2b9318cf989634e15af0e460a3bc3791a");

        assertEquals(e3.equals(e4), false);

    }

    /**
     * Test of toProto method, of class Entry.
     */
    @Test
    public void testToProto() {

        String id = "my-package@1.2.3";
        String name = "My Package";
        String desc = "my description...";
        String path = "http://nopath/mypackage.zip";
        long size = 1024L;
        String sha1 = "4b8fd1b2b9318cf989634e15af0e460a3bc3791a";

        Entry instance = new Entry(id, name, desc, path, size, sha1);

        eu.mihosoft.vrl.vupdater.proto.Entry expResult
                = eu.mihosoft.vrl.vupdater.proto.Entry.newBuilder().
                setId(id).setName(name).setDesc(desc).setPath(path).
                setSize(size).setSha1(sha1).
                build();
        eu.mihosoft.vrl.vupdater.proto.Entry result = instance.toProto();
        assertEquals(expResult, result);
    }

    /**
     * Test of fromProto method, of class Entry.
     */
    @Test
    public void testFromProto() {
        String id = "my-package@1.2.3";
        String name = "My Package";
        String desc = "my description...";
        String path = "http://nopath/mypackage.zip";
        long size = 1024L;
        String sha1 = "4b8fd1b2b9318cf989634e15af0e460a3bc3791a";

        eu.mihosoft.vrl.vupdater.proto.Entry protoE
                = eu.mihosoft.vrl.vupdater.proto.Entry.newBuilder().
                setId(id).setName(name).setDesc(desc).setPath(path).
                setSize(size).
                setSha1(sha1).
                build();

        Entry expResult = new Entry(id, name, desc, path, size, sha1);
        eu.mihosoft.vrl.vupdater.core.Entry result = Entry.fromProto(protoE);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Entry.
     */
    @Test
    public void testToString() {
        // ignore that
    }

}

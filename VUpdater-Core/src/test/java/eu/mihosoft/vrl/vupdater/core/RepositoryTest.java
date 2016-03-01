/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.vupdater.core;

import com.google.common.io.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
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
public class RepositoryTest {

    private File repoFile;

    public RepositoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        repoFile = new File(Files.createTempDir(), "repo.vrepo");
    }

    @After
    public void tearDown() {
        if (repoFile != null) {
            repoFile.delete();
        }
    }

    @Test
    public void testClone() {
        Repository r1 = new Repository();
        Entry e1 = new Entry("my-package@0.3.4", "My Package", "...", "path.zip");
        Delta d1 = new Delta("my-pkg@0.4.5", "my-pkg@1.2.3", "path1.zip");
        r1.addEntry(e1);
        r1.addDelta(d1);

        Repository r2 = new Repository(r1);
        assertEquals(r1, r2);

        // check that cloning was successful (i.e., no references)
        r2.removeEntry(e1);
        assertNotEquals(r1, r2);
    }

    /**
     * Test of getEntries method, of class Repository.
     */
    @Test
    public void testGetEntries() {
        Repository instance = new Repository();
        Entry e1 = new Entry("my-package@0.3.4", "My Package", "...", "path.zip");
        Entry e2 = new Entry("your-package@1.3.4", "My Package",  "...2", "path2.zip");
        instance.addEntry(e1);
        instance.addEntry(e2);
        List<Entry> expResult = Arrays.asList(new Entry[]{e1, e2});
        List<Entry> result = instance.getEntries();
        assertEquals(expResult, result);
    }

    /**
     * Test of addEntry method, of class Repository.
     */
    @Test
    public void testAddEntry() {
        Entry e = new Entry("my-package@0.3.4","My Package" , "...", "path.zip");
        Repository instance = new Repository();
        instance.addEntry(e);

        assertEquals(instance.getEntries().size(), 1);
        assertEquals(instance.getEntries().get(0), e);
    }

    /**
     * Test of removeEntry method, of class Repository.
     */
    @Test
    public void testRemoveEntry_Entry() {
        Entry e = new Entry("my-package@0.3.4", "My Package", "...", "path.zip");
        Repository instance = new Repository();
        instance.addEntry(e);

        boolean result1 = instance.removeEntry(e);

        assertEquals(instance.getEntries().size(), 0);
        boolean expResult1 = true;
        assertEquals(expResult1, result1);

        boolean result2 = instance.removeEntry(e);
        boolean expResult2 = false;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of removeEntry method, of class Repository.
     */
    @Test
    public void testRemoveEntry_String() {
        Entry e = new Entry("my-package@0.3.4", "My Package",  "...", "path.zip");
        Repository instance = new Repository();
        instance.addEntry(e);

        boolean result1 = instance.removeEntry(e.getId());
        assertEquals(instance.getEntries().size(), 0);

        boolean expResult1 = true;
        assertEquals(expResult1, result1);

        boolean result2 = instance.removeEntry(e.getId());
        boolean expResult2 = false;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getEntry method, of class Repository.
     */
    @Test
    public void testGetEntry() {
        int i = 0;
        Entry e = new Entry("my-package@0.3.4", "My Package", "...", "path.zip");
        Repository instance = new Repository();
        instance.addEntry(e);
        Entry result = instance.getEntry(i);
        assertEquals(e, result);
    }

    /**
     * Test of hasEntry method, of class Repository.
     */
    @Test
    public void testHasEntry() {

        String id1 = "my-package@0.3.4";
        Entry e = new Entry("my-package@0.3.4", "My Package", "...", "path.zip");
        Repository instance = new Repository();
        instance.addEntry(e);
        boolean expResult1 = true;
        boolean result1 = instance.hasEntry(id1);
        assertEquals(expResult1, result1);

        String id2 = "your-package@1.2.3";
        boolean expResult2 = false;
        boolean result2 = instance.hasEntry(id2);
        assertEquals(expResult2, result2);

    }

    /**
     * Test of getDeltas method, of class Repository.
     */
    @Test
    public void testGetDeltas() {

        Repository instance = new Repository();

        Delta d1 = new Delta("my-pkg@0.4.5", "my-pkg@1.2.3", "path1.zip");
        Delta d2 = new Delta("my-pkg@1.2.3", "my-pkg@1.2.4", "path2.zip");

        instance.addDelta(d1);
        instance.addDelta(d2);
        List<Delta> expResult = Arrays.asList(new Delta[]{d1, d2});
        List<Delta> result = instance.getDeltas();
        assertEquals(expResult, result);
    }

    /**
     * Test of addDelta method, of class Repository.
     */
    @Test
    public void testAddDelta() {
        Delta d = new Delta("my-pkg@0.4.5", "my-pkg@1.2.3", "path1.zip");
        Repository instance = new Repository();
        instance.addDelta(d);

        assertEquals(instance.getDeltas().size(), 1);
        assertEquals(instance.getDeltas().get(0), d);
    }

    /**
     * Test of removeDelta method, of class Repository.
     */
    @Test
    public void testRemoveDelta_Delta() {
        Delta d = new Delta("my-pkg@0.4.5", "my-pkg@1.2.3", "path1.zip");
        Repository instance = new Repository();
        instance.addDelta(d);
        boolean expResult1 = true;
        boolean result1 = instance.removeDelta(d);
        assertEquals(expResult1, result1);

        assertEquals(instance.getDeltas().size(), 0);

        boolean expResult2 = false;
        boolean result2 = instance.removeDelta(d);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of removeDelta method, of class Repository.
     */
    @Test
    public void testRemoveDelta_String_String() {
        String from = "my-pkg@0.4.5";
        String to = "my-pkg@1.2.3";
        Delta d1 = new Delta("my-pkg@0.4.5", "my-pkg@1.2.3", "path1.zip");
        Delta d2 = new Delta("my-pkg@1.2.3", "my-pkg@1.2.4", "path1.zip");

        Repository instance = new Repository();
        instance.addDelta(d1);
        instance.addDelta(d2);

        boolean expResult1 = true;
        boolean result1 = instance.removeDelta(from, to);
        assertEquals(expResult1, result1);
        assertEquals(instance.getDeltas().size(), 1);

        boolean expResult2 = false;
        boolean result2 = instance.removeDelta(from, to);
        assertEquals(expResult2, result2);
        assertEquals(instance.getDeltas().size(), 1);

    }

    /**
     * Test of getDelta method, of class Repository.
     */
    @Test
    public void testGetDelta_int() {
        System.out.println("getDelta");

        Delta d1 = new Delta("my-pkg@0.4.5", "my-pkg@1.2.3", "path1.zip");
        Delta d2 = new Delta("my-pkg@1.2.3", "my-pkg@1.2.4", "path1.zip");

        Repository instance = new Repository();
        instance.addDelta(d1);
        instance.addDelta(d2);

        int i1 = 0;
        Delta expResult1 = d1;
        Delta result1 = instance.getDelta(i1);
        assertEquals(expResult1, result1);

        int i2 = 1;
        Delta expResult2 = d2;
        Delta result2 = instance.getDelta(i2);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getDelta method, of class Repository.
     */
    @Test
    public void testGetDelta_String_String() {
        System.out.println("getDelta");

        Delta d1 = new Delta("my-pkg@0.4.5", "my-pkg@1.2.3", "path1.zip");
        Delta d2 = new Delta("my-pkg@1.2.3", "my-pkg@1.2.4", "path1.zip");

        Repository instance = new Repository();
        instance.addDelta(d1);
        instance.addDelta(d2);

        Delta expResult1 = d1;
        Delta result1 = instance.getDelta("my-pkg@0.4.5", "my-pkg@1.2.3");
        assertEquals(expResult1, result1);

        Delta expResult2 = d2;
        Delta result2 = instance.getDelta("my-pkg@1.2.3", "my-pkg@1.2.4");
        assertEquals(expResult2, result2);

        assertEquals(instance.getDelta("my-pkg@0.1", "my-pkg@0.2"), null);
        assertEquals(instance.getDelta("my-pkg@0.4.5", "my-pkg@1.2.4"), null);
    }

    /**
     * Test of hasDelta method, of class Repository.
     */
    @Test
    public void testHasDelta() {
        System.out.println("hasDelta");

        Delta d1 = new Delta("my-pkg@0.4.5", "my-pkg@1.2.3", "path1.zip");
        Delta d2 = new Delta("my-pkg@1.2.3", "my-pkg@1.2.4", "path1.zip");

        Repository instance = new Repository();
        instance.addDelta(d1);
        instance.addDelta(d2);

        String from1 = "my-pkg@0.4.5";
        String to1 = "my-pkg@1.2.3";

        boolean expResult1 = true;
        boolean result1 = instance.hasDelta(from1, to1);
        assertEquals(expResult1, result1);

        String from2 = "my-pkg@0.4.6";
        String to2 = "my-pkg@1.2.3";

        boolean expResult2 = false;
        boolean result2 = instance.hasDelta(from2, to2);
        assertEquals(expResult2, result2);

        String from3 = "my-pkg@1.2.3";
        String to3 = "my-pkg@1.2.4";

        boolean expResult3 = true;
        boolean result3 = instance.hasDelta(from3, to3);
        assertEquals(expResult3, result3);

        String from4 = "my-pkg@0.4.5";
        String to4 = "my-pkg@1.2.4";

        boolean expResult4 = false;
        boolean result4 = instance.hasDelta(from4, to4);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of getDescription method, of class Repository.
     */
    @Test
    public void testGetDescription() {
        Repository instance = new Repository();
        instance.setDescription("myDesc");
        String expResult = "myDesc";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Repository.
     */
    @Test
    public void testSetDescription() {
        Repository instance = new Repository();
        instance.setDescription("myDesc");
        String expResult = "myDesc";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Repository.
     */
    @Test
    public void testEquals() {
        Repository instance1 = new Repository();
        instance1.setDescription("This is a description.");
        instance1.addEntry(new Entry("another-pkg@0.2.3", "My Package", "mydesc", "path.zip"));
        instance1.addEntry(new Entry("another-pkg@0.2.4", "My Package", "mydesc", "path.zip"));
        instance1.addDelta(new Delta("another-pkg@0.2.3", "another-pkg@0.2.4", "path.zip"));

        Repository instance2 = new Repository();
        instance2.setDescription("This is a description.");
        instance2.addEntry(new Entry("another-pkg@0.2.3", "My Package", "mydesc test123", "path.zip"));
        instance2.addEntry(new Entry("another-pkg@0.2.4", "My Package", "mydesc2", "path.zip"));
        instance2.addDelta(new Delta("another-pkg@0.2.3", "another-pkg@0.2.4", "path.zip"));

        Repository instance3 = new Repository();
        instance2.setDescription("This is a description.");
        instance2.addEntry(new Entry("another-pkg@0.2.2", "My Package", "mydesc", "path.zip"));
        instance2.addEntry(new Entry("another-pkg@0.2.3", "My Package", "mydesc test123", "path.zip"));
        instance2.addEntry(new Entry("another-pkg@0.2.4", "My Package", "mydesc2", "path.zip"));
        instance2.addDelta(new Delta("another-pkg@0.2.3", "another-pkg@0.2.4", "path.zip"));

        assertEquals(instance1.equals(instance3), false);

        
        Assert.assertFalse(instance1.equals(null));
        Assert.assertFalse(instance1.equals(1));
    }

    /**
     * Test of hashCode method, of class Repository.
     */
    @Test
    public void testHashCode() {
        // too lazy :(
    }

    /**
     * Test of toString method, of class Repository.
     */
    @Test
    public void testToString() {
        // 
    }

    /**
     * Test of save method, of class Repository.
     */
    @Test
    public void testSave_OutputStream_Format() throws Exception {

        saveAndCompareStreamTest(Format.BINARY, "repo.vrepo");
        saveAndCompareStreamTest(Format.JSON, "repo.vrepo.json");

    }

    /**
     * Test of save method, of class Repository.
     */
    @Test
    public void testSave_File_Format() throws Exception {
        saveAndCompareFileTest(Format.BINARY, "repo.vrepo");
        FileUtils.copyFile(repoFile, new File("C:\\Users\\miho\\Documents\\tmp\\repo.vrepo"));
        saveAndCompareFileTest(Format.JSON, "repo.vrepo.json");
        FileUtils.copyFile(repoFile, new File("C:\\Users\\miho\\Documents\\tmp\\repo.vrepo.json"));
    }

    /**
     * Test of load method, of class Repository.
     */
    @Test
    public void testLoad_InputStream_Format() throws Exception {
        Repository instance1 = new Repository();
        instance1.setDescription("This is a description.");
        instance1.addEntry(new Entry("another-pkg@0.2.3", "My Package", "mydesc", "path.zip"));
        instance1.addEntry(new Entry("another-pkg@0.2.4", "My Package", "mydesc", "path.zip"));
        instance1.addDelta(new Delta("another-pkg@0.2.3", "another-pkg@0.2.4", "path.zip"));

        Repository instance2 = new Repository();

        instance2.load(RepositoryTest.class.
                getResourceAsStream("repo.vrepo"), Format.BINARY);
        assertEquals(instance1, instance2);

        Repository instance3 = new Repository();

        instance3.load(RepositoryTest.class.
                getResourceAsStream("repo.vrepo.json"), Format.JSON
        );
        assertEquals(instance1, instance3);

    }

    /**
     * Test of load method, of class Repository.
     */
    @Test
    public void testLoad_File_Format() throws Exception {
        Repository instance1 = new Repository();
        instance1.setDescription("This is a description.");
        instance1.addEntry(new Entry("another-pkg@0.2.3", "My Package", "mydesc", "path.zip"));
        instance1.addEntry(new Entry("another-pkg@0.2.4", "My Package", "mydesc", "path.zip"));
        instance1.addDelta(new Delta("another-pkg@0.2.3", "another-pkg@0.2.4", "path.zip"));

        Repository instance2 = new Repository();

        File fBinary = new File(Files.createTempDir(), "f.bin");

        FileUtils.copyInputStreamToFile(RepositoryTest.class.
                getResourceAsStream("repo.vrepo"), fBinary);

        instance2.load(fBinary, Format.BINARY);
        assertEquals(instance1, instance2);

        Repository instance3 = new Repository();

        File fJSON = new File(Files.createTempDir(), "f.json");

        FileUtils.copyInputStreamToFile(RepositoryTest.class.
                getResourceAsStream("repo.vrepo.json"), fJSON);

        instance3.load(fJSON, Format.JSON);

        assertEquals(instance1, instance3);
    }

    /**
     * Test of load method, of class Repository.
     */
    @Test
    public void testLoad_File() throws Exception {
        Repository instance1 = new Repository();
        instance1.setDescription("This is a description.");
        instance1.addEntry(new Entry("another-pkg@0.2.3", "My Package", "mydesc", "path.zip"));
        instance1.addEntry(new Entry("another-pkg@0.2.4", "My Package", "mydesc", "path.zip"));
        instance1.addDelta(new Delta("another-pkg@0.2.3", "another-pkg@0.2.4", "path.zip"));

        // test load binary file
        Repository instance2 = new Repository();
        File fBinary = new File(Files.createTempDir(), "f.bin");
        FileUtils.copyInputStreamToFile(RepositoryTest.class.
                getResourceAsStream("repo.vrepo"), fBinary);
        instance2.load(fBinary);
        assertEquals(instance1, instance2);

        // test load json file
        Repository instance3 = new Repository();
        File fJSON = new File(Files.createTempDir(), "f.json");
        FileUtils.copyInputStreamToFile(RepositoryTest.class.
                getResourceAsStream("repo.vrepo.json"), fJSON);
        instance3.load(fJSON);
        assertEquals(instance1, instance3);

        // test load arbitrary file (exception expected)
        Repository instance4 = new Repository();

        File fError = new File(Files.createTempDir(), "f.error");
        Files.write("error error".getBytes("UTF-8"), fError);
        IOException ex = null;
        try {
            instance4.load(fError);
        } catch (IOException e) {
            ex = e;
        }
        
        assertNotNull(ex);
        assertNotNull(ex.getMessage());
        assertTrue(ex.getMessage().toLowerCase().contains("format"));
    }

    private void saveAndCompareStreamTest(Format format, String origName) throws IOException, FileNotFoundException {
        OutputStream o = new FileOutputStream(repoFile);

        Repository instance = new Repository();
        instance.setDescription("This is a description.");
        instance.addEntry(new Entry("another-pkg@0.2.3", "My Package", "mydesc", "path.zip"));
        instance.addEntry(new Entry("another-pkg@0.2.4", "My Package", "mydesc", "path.zip"));
        instance.addDelta(new Delta("another-pkg@0.2.3", "another-pkg@0.2.4", "path.zip"));
        instance.save(o, format);

        byte[] bytes = IOUtils.toByteArray(RepositoryTest.class.
                getResourceAsStream(origName));

        // TODO maybe we should allow for content change if compatible
        String sha1Orig = DownloadImpl.generateSHA1Sum(bytes);
        String sha1New = DownloadImpl.generateSHA1Sum(repoFile);

        assertEquals(sha1Orig, sha1New);
    }

    private void saveAndCompareFileTest(Format format, String origName) throws IOException, FileNotFoundException {

        Repository instance = new Repository();
        instance.setDescription("This is a description.");
        instance.addEntry(new Entry("another-pkg@0.2.3", "My Package", "mydesc", "path.zip"));
        instance.addEntry(new Entry("another-pkg@0.2.4", "My Package", "mydesc", "path.zip"));
        instance.addDelta(new Delta("another-pkg@0.2.3", "another-pkg@0.2.4", "path.zip"));
        instance.save(repoFile, format);

        byte[] bytes = IOUtils.toByteArray(RepositoryTest.class.
                getResourceAsStream(origName));

        // TODO maybe we should allow for content change if compatible
        String sha1Orig = DownloadImpl.generateSHA1Sum(bytes);
        String sha1New = DownloadImpl.generateSHA1Sum(repoFile);

        assertEquals(sha1Orig, sha1New);
    }

    /**
     * Test of findDeltaUpdateFor method, of class Repository.
     */
    @Test
    public void testFindDeltaUpdateFor() {
        Repository instance = new Repository();
        instance.setDescription("This is a description.");
        instance.addEntry(new Entry("another-pkg@0.2.3", "My Package", "mydesc", "path.zip"));
        instance.addEntry(new Entry("another-pkg@0.2.4", "My Package", "mydesc", "path.zip"));
        instance.addEntry(new Entry("another-pkg@0.2.5", "My Package", "mydesc", "path.zip"));
        instance.addDelta(new Delta("another-pkg@0.2.3", "another-pkg@0.2.4", "path.zip"));
        instance.addDelta(new Delta("another-pkg@0.2.3", "another-pkg@0.2.5", "path.zip"));
        instance.addDelta(new Delta("another-pkg@0.2.4", "another-pkg@0.2.5", "path.zip"));

        Optional<Delta> update1 = instance.findDeltaUpdateFor("another-pkg@0.2.3");

        Assert.assertTrue(update1.isPresent());
        Assert.assertEquals(update1.get(),
                new Delta("another-pkg@0.2.3", "another-pkg@0.2.4", "path.zip"));

        Optional<Delta> update2 = instance.findDeltaUpdateFor("another-pkg@0.2.4");

        Assert.assertTrue(update2.isPresent());

        Assert.assertEquals(update2.get(),
                new Delta("another-pkg@0.2.4", "another-pkg@0.2.5", "path.zip"));

    }

    /**
     * Test of findUpdateFor method, of class Repository.
     */
    @Test
    public void testFindUpdateFor() {
        Repository instance = new Repository();
        instance.setDescription("This is a description.");
        instance.addEntry(new Entry("another-pkg@0.2.3", "My Package", "mydesc", "path.zip"));
        instance.addEntry(new Entry("another-pkg@0.2.4", "My Package", "mydesc", "path.zip"));
        instance.addDelta(new Delta("another-pkg@0.2.3", "another-pkg@0.2.4", "path.zip"));

        Optional<Entry> update1 = instance.findUpdateFor("another-pkg@0.2.3");
        Assert.assertTrue(update1.isPresent());
        Assert.assertEquals(update1.get(), new Entry("another-pkg@0.2.4", "My Package", "mydesc", "path.zip"));

        Optional<Entry> update2 = instance.findUpdateFor("another-pkg@0.2.4");
        Assert.assertFalse(update2.isPresent());

    }

}

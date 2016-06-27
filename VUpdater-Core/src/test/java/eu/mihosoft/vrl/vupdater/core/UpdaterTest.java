/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.vupdater.core;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import org.apache.commons.io.FileUtils;
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
public class UpdaterTest {

    public UpdaterTest() {
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

    private Repository createTestRepository() throws IOException {
        Repository instance = new Repository();
        instance.setDescription("This is a description.");
        instance.addEntry(new Entry("vrl-studio@0.4.5.6", "VRL-Studio", "mydesc",
                "http://vrl-studio.mihosoft.eu/releases/v0.4.5.6/VRL-Studio-Linux.zip", 150276088L,
                "48cedb4775d1c4a0cd1ceb05987f4c8ff4113183"));
        instance.addEntry(new Entry("vrl-studio@0.4.5.7", "VRL-Studio", "mydesc",
                "http://vrl-studio.mihosoft.eu/releases/v0.4.5.7/VRL-Studio-Linux.zip", 326443980L,
                "17ca58114de926b9b90521defaf180c5bf460618"));
        instance.addEntry(new Entry("another-pkg@0.2.5", "Another Package", "mydesc", "path.zip", 1024L));
        instance.addDelta(new Delta("another-pkg@0.2.3", "another-pkg@0.2.4", "path.zip", 1024L));
        instance.addDelta(new Delta("another-pkg@0.2.3", "another-pkg@0.2.5", "path.zip", 1024L));
        instance.addDelta(new Delta("another-pkg@0.2.4", "another-pkg@0.2.5", "path.zip", 1024L));

        return instance;
    }

    /**
     * Test of downloadRepository method, of class Updater.
     */
    @Test
    public void testDownloadRepository() throws InterruptedException, ExecutionException, IOException, TimeoutException {
        System.out.println("downloadRepository");
        Updater u = new Updater("vrl-studio@0.4.5.6",
                "https://bintray.com/artifact/download/miho/Ext/repository.vrepo");

        DownloadResult res = u.downloadRepository(
                (d) -> {
                    System.out.println("-> progress: " + d.getProgress());
                }).get(30, TimeUnit.SECONDS);

        Assert.assertTrue(!res.hasASC());
        Assert.assertTrue(!res.hasSHA1());
        Assert.assertTrue(res.isValid());

        Repository r = new Repository();
        r.load(res.getFile());

        Assert.assertTrue(r.equals(createTestRepository()));
    }

    /**
     * Test of downloadRepositoryAndVerifySHA1 method, of class Updater.
     */
    @Test
    public void testDownloadRepositoryAndVerifySHA1() throws InterruptedException, ExecutionException, TimeoutException {
        System.out.println("downloadRepositoryAndVerifySHA1");

        String sha1 = "d52f638b4ea69213d118688105b623c3ce91a713";
        Updater u = new Updater("vrl-studio@0.4.5.6",
                "https://bintray.com/artifact/download/miho/Ext/repository.vrepo");

        DownloadResult result = u.downloadRepositoryAndVerifySHA1(null, sha1).get(30, TimeUnit.SECONDS);

        assertFalse(result.hasASC());
        assertTrue(result.hasSHA1());
        assertTrue(result.isValid());
    }

    /**
     * Test of downloadRepositoryAndVerifyASC method, of class Updater.
     */
    @Test
    public void testDownloadRepositoryAndVerifyASC() throws InterruptedException, ExecutionException, IOException, TimeoutException {
        System.out.println("downloadRepositoryAndVerifyASC");
        Updater u = new Updater("vrl-studio@0.4.5.6",
                "https://bintray.com/artifact/download/miho/Ext/repository.vrepo",
                "http://keys.mihosoft.eu/bintray-key.asc");

        DownloadResult res = u.downloadRepositoryAndVerifyASC(
                (d) -> {
                    System.out.println("-> progress: " + d.getProgress());
                }).get(60, TimeUnit.SECONDS);

        Assert.assertTrue(res.hasASC());
        Assert.assertTrue(!res.hasSHA1());
        Assert.assertTrue(res.isValid());

        Repository r = new Repository();
        r.load(res.getFile());

        Assert.assertTrue(r.equals(createTestRepository()));
    }

    /**
     * Test of downloadAndVerifySHA1 method, of class Updater.
     */
    @Test
    public void testDownloadAndVerifySHA1() throws InterruptedException, ExecutionException, TimeoutException {
        // valid sha1 test
        String url = "https://bintray.com/artifact/download/miho/Ext/test-update-0.1.zip";
        String sha1 = "6f820042cdbafdb19990acfd8d58c8337996e994";
        Updater instance = new Updater("", "");
        DownloadResult result = instance.downloadAndVerifySHA1(url, sha1, null).
                get(240, TimeUnit.SECONDS);
        assertFalse(result.hasASC());
        assertTrue(result.hasSHA1());
        assertTrue(result.isValid());

        // same with invalid sha1
        sha1 = "6f820042cdbafdb19990acfd8d58c8337996e992";
        result = instance.downloadAndVerifySHA1(url, sha1, null).
                get(240, TimeUnit.SECONDS);
        assertFalse(result.hasASC());
        assertTrue(result.hasSHA1());
        assertFalse(result.isValid());
    }

    /**
     * Test of downloadAndVerifyASC method, of class Updater.
     */
    @Test
    public void testDownloadAndVerifyASC() throws InterruptedException, ExecutionException, TimeoutException {
        String url = "https://bintray.com/artifact/download/miho/Ext/test-update-0.1.zip";
        String publicKeyURL = "http://keys.mihosoft.eu/bintray-key.asc";

        Updater instance = new Updater(url, publicKeyURL);
        DownloadResult result = instance.downloadAndVerifyASC(
                url, publicKeyURL, null).get(240, TimeUnit.SECONDS);

        assertTrue(result.hasASC());
        assertTrue(result.isValid());

        assertTrue(result.getFile() != null);
    }

    /**
     * Test of download method, of class Updater.
     */
    @Test
    public void testDownload_String() throws InterruptedException, ExecutionException, TimeoutException {
        String url = "https://bintray.com/artifact/download/miho/Ext/test-update-0.1.zip";
        Updater instance = new Updater("", "");

        DownloadResult result = instance.download(url).get(240, TimeUnit.SECONDS);

        assertTrue(!result.hasASC());
        assertTrue(!result.hasSHA1());
        assertTrue(result.isValid());
    }

    /**
     * Test of download method, of class Updater.
     */
    @Test
    public void testDownload_String_BiConsumer() throws InterruptedException, ExecutionException, TimeoutException {
        String url = "https://bintray.com/artifact/download/miho/Ext/test-update-0.1.zip";

        int[] statusArr = {-1};
        float[] progressArr = {-1};

        Consumer<Download> c = (d) -> {
            System.out.println("->progress: " + d.getProgress());
            statusArr[0] = d.getStatus();
            progressArr[0] = d.getProgress();
        };
        Updater instance = new Updater("", "");
        DownloadResult result = instance.download(url, c).
                get(240, TimeUnit.SECONDS);

        assertTrue(!result.hasASC());
        assertTrue(!result.hasSHA1());
        assertTrue(result.isValid());

        assertEquals(statusArr[0], Download.COMPLETE);
        assertEquals(progressArr[0], 100.f, 0.01);
    }

    @Test
    public void sha1InvalidUsageTest() {

        String sha1
                = DownloadImpl.generateSHA1Sum(
                        new File("/some/nonexistent/file.bin"));

        Assert.assertEquals("", sha1);

        Exception ex = null;
        File f = null;
        try {
            DownloadImpl.generateSHA1Sum(f);
        } catch (NullPointerException e) {
            ex = e;
        }
        
        Assert.assertNotNull(ex);
        
        ex = null;
        byte[] data = null;
        try {
            DownloadImpl.generateSHA1Sum(data);
        } catch (NullPointerException e) {
            ex = e;
        }
        
        Assert.assertNotNull(ex);

    }

}

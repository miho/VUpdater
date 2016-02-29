/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.vupdater.core;

import com.google.common.io.Files;
import static eu.mihosoft.vrl.vupdater.core.DownloadImpl.generateSHA1Sum;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static eu.mihosoft.vrl.vupdater.core.DownloadImpl.generateSHA1Sum;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class Updater {

    private final String id;
    private final String repositoryURL;
    private final String publicASCKeyURL;

    public Updater(String id, String repositoryURL) {
        this(id, repositoryURL, "");
    }

    public Updater(String id, String repositoryURL, String publicASCKeyURL) {
        this.id = id;
        this.repositoryURL = repositoryURL;
        this.publicASCKeyURL = publicASCKeyURL;
    }

    public CompletableFuture<DownloadResult> downloadRepository(
            Consumer<Download> c) {
        return download(repositoryURL, c);
    }

    public CompletableFuture<DownloadResult> downloadRepositoryAndVerifySHA1(
            Consumer<Download> c, String sha1) {
        return downloadAndVerifySHA1(repositoryURL, sha1, c);
    }

    public CompletableFuture<DownloadResult> downloadRepositoryAndVerifyASC(
            Consumer<Download> c) {
        return downloadAndVerifyASC(repositoryURL, publicASCKeyURL, c);
    }

    public CompletableFuture<DownloadResult> downloadAndVerifySHA1(
            String url, String sha1, Consumer<Download> c) {
        CompletableFuture<DownloadResult> result = new CompletableFuture<>();
        new Thread(() -> {
            try {
                DownloadResult file = download(url, c).get();

                result.complete(new DownloadResult() {

                    @Override
                    public File getFile() {
                        return file.getFile();
                    }

                    @Override
                    public boolean hasASC() {
                        return false;
                    }

                    @Override
                    public boolean hasSHA1() {
                        return true;
                    }

                    @Override
                    public boolean isValid() {
                        return sha1.equals(generateSHA1Sum(file.getFile()));
                    }
                });

            } catch (InterruptedException | ExecutionException | RuntimeException ex) {
                result.completeExceptionally(ex);
            }

        }).start();

        return result;
    }

    public CompletableFuture<DownloadResult> downloadAndVerifyASC(
            String url, String publicKeyURL, Consumer<Download> c) {
        CompletableFuture<DownloadResult> result = new CompletableFuture<>();
        new Thread(() -> {
            try {
                DownloadResult key = download(publicKeyURL, c).get(60,TimeUnit.SECONDS);
                DownloadResult sig = download(url + ".asc", c).get(60,TimeUnit.SECONDS);
                DownloadResult file = download(url, c).get();

                final boolean verficationRes
                        = VPGPUtil.verifyFile(
                                key.getFile(),
                                file.getFile(),
                                sig.getFile());

                result.complete(new DownloadResult() {

                    @Override
                    public File getFile() {
                        return file.getFile();
                    }

                    @Override
                    public boolean hasASC() {
                        return true;
                    }

                    @Override
                    public boolean hasSHA1() {
                        return false;
                    }

                    @Override
                    public boolean isValid() {
                        return verficationRes;
                    }
                });

            } catch (InterruptedException | ExecutionException | IOException | RuntimeException ex) {
                result.completeExceptionally(ex);
            } catch (TimeoutException ex) {
                Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
                result.completeExceptionally(ex);
            }

        }).start();

        return result;
    }

    public CompletableFuture<DownloadResult> download(String url) {
        return download(url, null);
    }

    public CompletableFuture<DownloadResult> download(String url,
            Consumer<Download> c) {

        CompletableFuture<DownloadResult> result = new CompletableFuture<>();
        try {

            File repoDir = Files.createTempDir();
            DownloadImpl d = new DownloadImpl(new URL(url), repoDir, 5000, 5000);
            d.addObserver((o, arg) -> {

                if (c != null) {
                    c.accept(d);
                }

                if (d.getStatus() == DownloadImpl.COMPLETE) {
                    result.complete(new DownloadResult() {
                        @Override
                        public File getFile() {
                            return d.getTargetFile();
                        }

                        @Override
                        public boolean isValid() {
                            return true;
                        }
                    });
                }
            });

            d.download();
        } catch (MalformedURLException ex) {
            result.completeExceptionally(ex);
        }

        return result;
    }

}

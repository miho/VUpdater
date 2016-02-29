/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.vupdater.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Checksum;
import org.apache.commons.io.FileUtils;

/**
 * Downloads a file from the specified url. This class can be observed (for
 * progress and download state).
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 *
 * Implementation based on
 * {@linkplain http://www.java-tips.org/java-se-tips/javax.swing/how-to-create-a-download-manager-in-java.html}
 */
class DownloadImpl extends Observable implements Runnable, Download {

    // Max size of download buffer.
    private static final int MAX_BUFFER_SIZE = 8192;
    // These are the status names.
    public static final String STATUSES[] = {"Downloading",
        "Paused", "Complete", "Cancelled", "Error"};
    // These are the status codes.

    private final URL url; // download URL
    private int size; // size of download in bytes
    private int downloaded; // number of bytes downloaded
    private int status; // current status of download
    private int connectionTimeout = 5000; // connection timeout in milliseconds
    private int readTimeout = 60 * 1000; // read timeout (max. download duration)
    private final File location; // location (download folder)

    /**
     * Constructor.
     *
     * @param url url
     * @param location download location (folder)
     * @param connectionTimeout connection timout (max. connection time)
     * @param readTimeout read timeout (max. download time)
     */
    public DownloadImpl(URL url, File location,
            int connectionTimeout, int readTimeout) {
        this.url = url;
        size = -1;
        downloaded = 0;
        status = DOWNLOADING;

        this.location = location;

        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
    }

    /**
     * Returns this download's URL.
     *
     * @return this download's URL
     */
    public String getUrl() {
        return url.toString();
    }

    /**
     * Returns this download's size in bytes.
     *
     * @return this download's size in bytes
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns this download's progress (%).
     *
     * @return this download's progress (%)
     */
    @Override
    public float getProgress() {
        return ((float) downloaded / size) * 100;
    }

    /**
     * Get this download's status.
     *
     * @return status
     */
    @Override
    public int getStatus() {
        return status;
    }

    /**
     * Pauses this download.
     */
    public void pause() {
        status = PAUSED;
        stateChanged();
    }

    /**
     * Resumes this download.
     */
    public void resume() {
        status = DOWNLOADING;
        stateChanged();
        download();
    }

    /**
     * Cancels this download.
     */
    public void cancel() {
        status = CANCELLED;
        stateChanged();
    }

    /**
     * Marks this download as having an error.
     */
    private void error() {
        status = ERROR;
        stateChanged();
    }

    /**
     * Starts or resumes downloading.
     *
     * @return download thread
     */
    public Thread download() {
        Thread thread = new Thread(this);
        thread.start();
        return thread;
    }

    /**
     * Returns the file name portion of the URL.
     */
    private String getFileName(URL url) {
        String fileName = url.getFile();
        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }

    /**
     * Download the specified file.
     */
    @Override
    public void run() {
        RandomAccessFile file = null;

        InputStream stream = null;

        try {

            // Open connection to URL.
            HttpURLConnection connection
                    = (HttpURLConnection) url.openConnection();

            // Specify what portion of file to download.
            connection.setRequestProperty("Range",
                    "bytes=" + downloaded + "-");

            connection.setConnectTimeout(connectionTimeout);
            connection.setReadTimeout(readTimeout);

            // Connect to server.
            connection.connect();

            // Make sure response code is in the 200 range.
            if (connection.getResponseCode() / 100 != 2) {
                System.err.println(">> Download: ERROR: response-code: " + connection.getResponseCode());
                error();
            }

            // Check for valid content length.
            int contentLength = connection.getContentLength();
            if (contentLength < 1) {
                System.err.println(">> Download: ERROR: len = " + contentLength);
                error();
            }

            /* Set the size for this download if it
             hasn't been already set. */
            if (size == -1) {
                size = contentLength;
                stateChanged();
            }

            // Open file and seek to the end of it.
            file = new RandomAccessFile(new File(location, getFileName(url)), "rw");
            file.seek(downloaded);
            stream = connection.getInputStream();

            /* Size buffer according to how much of the
                 file is left to download. */
            byte[] buffer = new byte[MAX_BUFFER_SIZE];
            double prevProgress = 0;
            while (status == DOWNLOADING) {

                // Read from server into buffer.
                int read = stream.read(buffer, 0, MAX_BUFFER_SIZE);
                if (read == -1) {
                    break;
                }

//                System.out.println("read: " +read);
                // Write buffer to file.
                file.write(buffer, 0, read);
                downloaded += read;
                double progress = getProgress();
                if (progress - prevProgress > 0.5) {
                    stateChanged();
                    prevProgress = progress;
                }
            }

            /* Change status to complete if this point was
             reached because downloading has finished. */
            if (status == DOWNLOADING) {
                status = COMPLETE;
                try {
                    stateChanged();
                } catch (Exception ex) {
                    ex.printStackTrace(System.err);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            error();
        } finally {

            if (file != null) {
                try {
                    file.close();
                } catch (Exception ex) {
                    Logger.getLogger(DownloadImpl.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }

            // Close connection to server.
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    /**
     * Notify observers that this download's status has changed.
     */
    private void stateChanged() {
        setChanged();
        notifyObservers();
    }

    /**
     * @return the location
     */
    public File getTargetFile() {
        return new File(location, getFileName(url));
    }

//    /**
//     * Verifies the downloaded file with the specified checksum.
//     *
//     * @param checksum checksum
//     * @return <code>true</code> if the verification was successful;
//     * <code>false</code> otherwise
//     */
//    public boolean verifyMD5(String checksum) {
//        if (getStatus() != COMPLETE) {
//            throw new IllegalStateException(
//                    "verification impossible. This download is incomplete!");
//        }
//
//        return IOUtil.verifyFileMD5(getTargetFile(), checksum);
//    }
    /**
     * Verifies the downloaded file with the specified checksum.
     *
     * @param checksum checksum
     * @return <code>true</code> if the verification was successful;
     * <code>false</code> otherwise
     */
    public boolean verifySHA1(String checksum) {
        if (getStatus() != COMPLETE) {
            throw new IllegalStateException(
                    "verification impossible. This download is incomplete!");
        }

        return checksum.equals(generateSHA1Sum(getTargetFile()));
    }

    static String generateSHA1Sum(File f) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            return convertToHex(md.digest(fileToByteArray(f)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DownloadImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DownloadImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DownloadImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return "";
    }
    
    static String generateSHA1Sum(byte[] data) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            return convertToHex(md.digest(data));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DownloadImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return "";
    }

    /**
     * Converts a byte array to hexadecimal String.
     *
     * @param data the data to convert
     * @return the data as hexa decimal string
     */
    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    /**
     * Loads data from file and converts it to a byte array.
     *
     * @param file the file to convert
     * @return a byte arry containing the converted data
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    static byte[] fileToByteArray(File file)
            throws FileNotFoundException, IOException {
        byte[] result;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            result = new byte[(int) file.length()];
            fileInputStream.read(result);
        }

        return result;
    }
}

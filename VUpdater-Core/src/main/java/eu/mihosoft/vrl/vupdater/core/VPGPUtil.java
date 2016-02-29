package eu.mihosoft.vrl.vupdater.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;

/**
 * Utility class for PGP key generation, signing and verification. This class is
 * only functional if the bcpg-*.jar files are on the classpath. As these
 * Jar-Files are signed they cannot be included in the VRL.jar.
 * <p>
 * These methods use the PGP implementation from http://www.bouncycastle.org/ .
 * </p>
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
class VPGPUtil {

    private VPGPUtil() {
        throw new AssertionError();
    }

//    /**
//     * Creates an RSA key pair for PGP signing and verification.
//     *
//     * @param identity the identity, e.g., "mycompany.com"
//     * @param password the password for the private key
//     * @param pubKeyFile public key file (will be created/overwritten)
//     * @param privKeyFile private key file (will be created/overwritten)
//     * @param ascii defines whether to use ASCII format
//     * @throws IOException if key files cannot be writen
//     * @throws RuntimeException if PGP algorithms failed or security provider hasn't
//     * been accepted
//     */
//    public static void createKeyPair(
//            String identity, String password,
//            File pubKeyFile, File privKeyFile, boolean ascii)
//            throws IOException, RuntimeException {
//
//        addProviderIfNecessary();
//        try {
//            RSAKeyPairGenerator.createKeyPair(
//                    identity, password, ascii, pubKeyFile, privKeyFile);
//        } catch (PGPException ex) {
//            Logger.getLogger(PGPUtil.class.getName()).log(Level.SEVERE, null, ex);
//            throw new RuntimeException(ex);
//        }
//    }

//    /**
//     * Signs a file. This method creates a signature file for the specified
//     * file.
//     *
//     * @param privKeyFile the private key
//     * @param password the password for the private key
//     * @param file the file to sign
//     * @param signatureFile the signature file (will be created/overwritten)
//     * @param ascii defines whether to use ASCII format
//     * @throws IOException if files cannot be found or the signature cannot be
//     * written
//     * @throws RuntimeException if PGP algorithms failed or security provider hasn't
//     * been accepted
//     */
//    public static void signFile(File privKeyFile, String password,
//            File file, File signatureFile, boolean ascii)
//            throws IOException, RuntimeException {
//
//        addProviderIfNecessary();
//        try {
//            DetachedSignatureProcessor.signFile(privKeyFile,
//                    password, file,
//                    signatureFile, ascii);
//        } catch (PGPException ex) {
//            Logger.getLogger(PGPUtil.class.getName()).log(Level.SEVERE, null, ex);
//            throw new RuntimeException(ex);
//        }
//    }

    /**
     * Verifies a file. This method uses a signature file and a public key to
     * verify the specified file.
     *
     * @param pubKeyFile public key file
     * @param file file to verify
     * @param signatureFile signature file
     * @return <code>true</code> if verification was successful;
     * <code>false</code> otherwise
     * @throws IOException if files cannot be read
     * @throws RuntimeException if PGP algorithms failed or security provider hasn't
     * been accepted
     */
    public static boolean verifyFile(
            File pubKeyFile, File file, File signatureFile)
            throws IOException, RuntimeException {

        addProviderIfNecessary();
        try {
            return DetachedSignatureProcessor.verifyFile(
                    pubKeyFile, file, signatureFile);
        } catch (PGPException ex) {
            Logger.getLogger(VPGPUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * Adds the BC provider if necessary, i.e., if the provider hasn't already
     * been added.
     */
    private static void addProviderIfNecessary() {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }
    
    /**
     * A simple routine that opens a key ring file and loads the first available
     * key suitable for signature generation.
     *
     * @param input stream to read the secret key ring collection from.
     * @return a secret key.
     * @throws IOException on a problem with using the input stream.
     * @throws PGPException if there is an issue parsing the input stream.
     */
    static PGPSecretKey readSecretKey(InputStream input) throws IOException, PGPException {
        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(
                org.bouncycastle.openpgp.PGPUtil.getDecoderStream(input));

        //
        // we just loop through the collection till we find a key suitable for encryption, in the real
        // world you would probably want to be a bit smarter about this.
        //

        Iterator keyRingIter = pgpSec.getKeyRings();
        while (keyRingIter.hasNext()) {
            PGPSecretKeyRing keyRing = (PGPSecretKeyRing) keyRingIter.next();

            Iterator keyIter = keyRing.getSecretKeys();
            while (keyIter.hasNext()) {
                PGPSecretKey key = (PGPSecretKey) keyIter.next();

                if (key.isSigningKey()) {
                    return key;
                }
            }
        }

        throw new IllegalArgumentException("Can't find signing key in key ring.");
    }
}

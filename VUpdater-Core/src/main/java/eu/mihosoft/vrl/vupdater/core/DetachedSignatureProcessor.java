package eu.mihosoft.vrl.vupdater.core;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureList;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;

/**
 * A simple utility class that creates seperate signatures for files and
 * verifies them. <p> To sign a file: DetachedSignatureProcessor -s [-a]
 * fileName secretKey passPhrase.<br> If -a is specified the output file will be
 * "ascii-armored". <p> To decrypt: DetachedSignatureProcessor -v fileName
 * signatureFile publicKeyFile. <p> Note: this example will silently overwrite
 * files. It also expects that a single pass phrase will have been used.
 */
class DetachedSignatureProcessor {

    private static boolean verifySignature(
            String fileName,
            String inputFileName,
            String keyFileName)
            throws GeneralSecurityException, IOException, PGPException {
        InputStream in = new BufferedInputStream(new FileInputStream(inputFileName));
        InputStream keyIn = new BufferedInputStream(new FileInputStream(keyFileName));

        boolean result = verifySignature(fileName, in, keyIn);

        keyIn.close();
        in.close();

        return result;
    }

    /*
     * verify the signature in in against the file fileName.
     */
    private static boolean verifySignature(
            String fileName,
            InputStream in,
            InputStream keyIn)
            throws GeneralSecurityException, IOException, PGPException {
        in = PGPUtil.getDecoderStream(in);

        PGPObjectFactory pgpFact = new PGPObjectFactory(in);
        PGPSignatureList p3;

        Object o = pgpFact.nextObject();
        if (o instanceof PGPCompressedData) {
            PGPCompressedData c1 = (PGPCompressedData) o;

            pgpFact = new PGPObjectFactory(c1.getDataStream());

            p3 = (PGPSignatureList) pgpFact.nextObject();
        } else {
            p3 = (PGPSignatureList) o;
        }

        PGPPublicKeyRingCollection pgpPubRingCollection =
                new PGPPublicKeyRingCollection(PGPUtil.getDecoderStream(keyIn));


        InputStream dIn = new BufferedInputStream(new FileInputStream(fileName));

        PGPSignature sig = p3.get(0);
        PGPPublicKey key = pgpPubRingCollection.getPublicKey(sig.getKeyID());

        sig.init(new JcaPGPContentVerifierBuilderProvider().setProvider("BC"), key);

        int ch;
        while ((ch = dIn.read()) >= 0) {
            sig.update((byte) ch);
        }

        dIn.close();

        if (sig.verify()) {
//            System.out.println("signature verified.");
            return true;
        } else {
//            System.out.println("signature verification failed.");
            return false;
        }
    }

//    private static void createSignature(
//            String inputFileName,
//            String keyFileName,
//            String outputFileName,
//            char[] pass,
//            boolean armor)
//            throws GeneralSecurityException, IOException, PGPException {
//        InputStream keyIn = new BufferedInputStream(new FileInputStream(keyFileName));
//        OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFileName));
//
//        createSignature(inputFileName, keyIn, out, pass, armor);
//
//        out.close();
//        keyIn.close();
//    }

    private static void createSignature(
            String fileName,
            InputStream keyIn,
            OutputStream out,
            char[] pass,
            boolean armor)
            throws GeneralSecurityException, IOException, PGPException {
        if (armor) {
            out = new ArmoredOutputStream(out);
        }

        PGPSecretKey pgpSec = VPGPUtil.readSecretKey(keyIn);
        PGPPrivateKey pgpPrivKey = pgpSec.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(pass));
        PGPSignatureGenerator sGen = new PGPSignatureGenerator(new JcaPGPContentSignerBuilder(pgpSec.getPublicKey().getAlgorithm(), PGPUtil.SHA1).setProvider("BC"));

        sGen.init(PGPSignature.BINARY_DOCUMENT, pgpPrivKey);

        BCPGOutputStream bOut = new BCPGOutputStream(out);

        InputStream fIn = new BufferedInputStream(new FileInputStream(fileName));

        int ch;
        while ((ch = fIn.read()) >= 0) {
            sGen.update((byte) ch);
        }

        fIn.close();

        sGen.generate().encode(bOut);

        if (armor) {
            out.close();
        }
    }

//    public static void signFile(File keyFile, String password, File file, boolean ascii)
//            throws Exception {
////        Security.addProvider(new BouncyCastleProvider());
//
//        if (ascii) {
//            signFile(
//                    keyFile,
//                    password, file, new File(
//                    file.getAbsolutePath() + ".asc"), true);
//        } else {
//            createSignature(
//                    file.getAbsolutePath(),
//                    keyFile.getAbsolutePath(),
//                    file.getAbsolutePath() + ".bpg", password.toCharArray(), false);
//        }
//
//    }
//
//    public static void signFile(File keyFile, String password, File file, File signatureFile, boolean ascii) throws IOException, PGPException {
//
////        Security.addProvider(new BouncyCastleProvider());
//
//        if (ascii) {
//            try {
//                createSignature(
//                        file.getAbsolutePath(),
//                        keyFile.getAbsolutePath(),
//                        signatureFile.getAbsolutePath(), password.toCharArray(), true);
//            } catch (GeneralSecurityException ex) {
//                Logger.getLogger(DetachedSignatureProcessor.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else {
//            try {
//                createSignature(
//                        file.getAbsolutePath(),
//                        keyFile.getAbsolutePath(),
//                        signatureFile.getAbsolutePath(), password.toCharArray(), false);
//            } catch (GeneralSecurityException ex) {
//                Logger.getLogger(DetachedSignatureProcessor.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//    }

    public static boolean verifyFile(File keyFile, File file, File signatureFile) throws IOException, PGPException {
        try {
//            Security.addProvider(new BouncyCastleProvider());

            return verifySignature(file.getAbsolutePath(),
                    signatureFile.getAbsolutePath(), keyFile.getAbsolutePath());
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(DetachedSignatureProcessor.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
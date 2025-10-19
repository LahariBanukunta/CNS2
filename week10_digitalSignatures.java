//Week 10: Write a program in java, which performs a Digital Signature on a given text.


import java.security.*;
import java.util.Base64;

public class Main{

    public static void main(String[] args) throws Exception {
        String message = "Welcome to Cryptography and Network Security Lab.";

        KeyPair keyPair = generateKeyPair();

        String signedMessage = signMessage(message, keyPair.getPrivate());

        boolean isVerified = verifySignature(message, signedMessage, keyPair.getPublic());

        System.out.println("Original Message: " + message);
        System.out.println("Signed Message: " + signedMessage);
        System.out.println("Signature Verified: " + isVerified);
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static String signMessage(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        byte[] signedData = signature.sign();
        return Base64.getEncoder().encodeToString(signedData);
    }

    public static boolean verifySignature(String message, String signedMessage, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes());
        byte[] signedData = Base64.getDecoder().decode(signedMessage);
        return signature.verify(signedData);
    }
}

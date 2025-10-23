import java.security.*;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws Exception {
        String message = "Welcome to Cryptography and Network Security Lab.";

        // Generate RSA key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        // Sign the message
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(keyPair.getPrivate());
        sign.update(message.getBytes());
        String signature = Base64.getEncoder().encodeToString(sign.sign());

        // Verify the signature
        Signature verify = Signature.getInstance("SHA256withRSA");
        verify.initVerify(keyPair.getPublic());
        verify.update(message.getBytes());
        boolean isVerified = verify.verify(Base64.getDecoder().decode(signature));

        // Output
        System.out.println("Original Message: " + message);
        System.out.println("Digital Signature: " + signature);
        System.out.println("Signature Verified: " + isVerified);
    }
}

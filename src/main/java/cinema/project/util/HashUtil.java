package cinema.project.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static final String SHA_512 = "SHA-512";

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder stringBuilder = new StringBuilder();
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(SHA_512);
            messageDigest.update(salt);
            byte[] hash = messageDigest.digest(password.getBytes());
            for (byte b : hash) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Could not create hash using SHA-512 algorithm", ex);
        }
    }

    public static byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }
}

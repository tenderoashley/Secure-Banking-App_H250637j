import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Represents a registered user of the banking application.
 * Passwords are never stored in plain text — only a SHA-256 hash is kept,
 * demonstrating basic secure coding practice.
 */
public class User {
    private String username;
    private String passwordHash;

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    public boolean checkPassword(String password) {
        return passwordHash.equals(hashPassword(password));
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    /** Serializes this user as a single line for storage in users.txt */
    public String toFileString() {
        return username + "," + passwordHash;
    }

    public static User fromFileString(String line) {
        String[] parts = line.split(",", 2);
        return new User(parts[0], parts[1]);
    }
}

package com.hyperswitch.common.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Password utility for hashing and verifying passwords
 * Uses SHA-256 with salt for password hashing
 */
public final class PasswordUtil {
    
    private static final String ALGORITHM = "SHA-256";
    private static final int SALT_LENGTH = 16;
    
    private PasswordUtil() {
        // Utility class - prevent instantiation
    }
    
    /**
     * Hash a password with a random salt
     */
    public static String hashPassword(String password) {
        try {
            // Generate random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);
            
            // Hash password with salt
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.update(salt);
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            
            // Combine salt and hash
            byte[] combined = new byte[salt.length + hash.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(hash, 0, combined, salt.length, hash.length);
            
            // Return base64 encoded string
            return Base64.getEncoder().encodeToString(combined);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Failed to hash password: " + ALGORITHM + " algorithm not available", e);
        }
    }
    
    /**
     * Verify a password against a hash
     */
    public static boolean verifyPassword(String password, String hash) {
        try {
            // Decode the hash
            byte[] combined = Base64.getDecoder().decode(hash);
            
            // Extract salt and hash
            byte[] salt = new byte[SALT_LENGTH];
            byte[] storedHash = new byte[combined.length - SALT_LENGTH];
            System.arraycopy(combined, 0, salt, 0, SALT_LENGTH);
            System.arraycopy(combined, SALT_LENGTH, storedHash, 0, storedHash.length);
            
            // Hash the provided password with the same salt
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.update(salt);
            byte[] computedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            
            // Compare hashes
            return MessageDigest.isEqual(storedHash, computedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Failed to verify password: " + ALGORITHM + " algorithm not available", e);
        } catch (IllegalArgumentException e) {
            // Invalid hash format
            return false;
        }
    }
}


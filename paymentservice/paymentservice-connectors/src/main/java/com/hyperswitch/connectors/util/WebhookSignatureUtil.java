package com.hyperswitch.connectors.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for webhook signature verification
 */
public final class WebhookSignatureUtil {

    private static final String HMAC_SHA256 = "HmacSHA256";

    private WebhookSignatureUtil() {
        // Utility class
    }

    /**
     * Verify Stripe webhook signature using HMAC-SHA256
     * Stripe signs webhooks using HMAC-SHA256 with the webhook signing secret
     */
    public static boolean verifyStripeSignature(String payload, String signature, String secret) {
        if (payload == null || signature == null || secret == null || secret.isEmpty()) {
            return false;
        }

        try {
            Mac mac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
            mac.init(secretKeySpec);
            
            byte[] hash = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            String expectedSignature = bytesToHex(hash);
            
            // Stripe sends signature in format: timestamp,signature
            // We need to extract the signature part if it contains a comma
            String signatureToCompare = signature;
            if (signature.contains(",")) {
                String[] parts = signature.split(",", 2);
                if (parts.length == 2) {
                    signatureToCompare = parts[1];
                }
            }
            
            return expectedSignature.equals(signatureToCompare);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            return false;
        }
    }

    /**
     * Convert byte array to hexadecimal string
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}


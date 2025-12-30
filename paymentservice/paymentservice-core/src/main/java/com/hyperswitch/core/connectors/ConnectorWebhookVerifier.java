package com.hyperswitch.core.connectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;

/**
 * Service for verifying webhook signatures per connector
 * Implements connector-specific signature verification algorithms
 */
@Service
public class ConnectorWebhookVerifier {
    
    private static final Logger log = LoggerFactory.getLogger(ConnectorWebhookVerifier.class);
    
    /**
     * Verify webhook signature for a connector
     */
    public boolean verifyWebhookSignature(
            String connectorName,
            String payload,
            String signature,
            String secret) {
        
        if (signature == null || secret == null || payload == null) {
            log.warn("Missing signature, secret, or payload for connector: {}", connectorName);
            return false;
        }
        
        try {
            switch (connectorName.toLowerCase()) {
                case "stripe":
                    return verifyStripeSignature(payload, signature, secret);
                case "paypal":
                    return verifyPayPalSignature(payload, signature, secret);
                case "razorpay":
                    return verifyRazorpaySignature(payload, signature, secret);
                case "adyen":
                    return verifyAdyenSignature(payload, signature, secret);
                default:
                    // Default HMAC-SHA256 verification
                    return verifyHmacSha256(payload, signature, secret);
            }
        } catch (Exception e) {
            log.error("Error verifying webhook signature for connector {}: {}", 
                connectorName, e.getMessage(), e);
            return false;
        }
    }
    
    /**
     * Verify Stripe webhook signature
     * Stripe uses HMAC-SHA256 with timestamp and payload
     */
    private boolean verifyStripeSignature(String payload, String signature, String secret) {
        try {
            // Stripe signature format: t=timestamp,v1=signature
            String[] parts = signature.split(",");
            String timestamp = null;
            String sig = null;
            
            for (String part : parts) {
                if (part.startsWith("t=")) {
                    timestamp = part.substring(2);
                } else if (part.startsWith("v1=")) {
                    sig = part.substring(3);
                }
            }
            
            if (timestamp == null || sig == null) {
                return false;
            }
            
            // Verify timestamp (should be within 5 minutes)
            long timestampLong = Long.parseLong(timestamp);
            long currentTime = System.currentTimeMillis() / 1000;
            if (Math.abs(currentTime - timestampLong) > 300) { // 5 minutes
                log.warn("Stripe webhook timestamp too old or too far in future");
                return false;
            }
            
            // Compute signature
            String signedPayload = timestamp + "." + payload;
            String computedSig = computeHmacSha256(signedPayload, secret);
            
            return MessageDigest.isEqual(
                computedSig.getBytes(StandardCharsets.UTF_8),
                sig.getBytes(StandardCharsets.UTF_8)
            );
        } catch (Exception e) {
            log.error("Error verifying Stripe signature: {}", e.getMessage(), e);
            return false;
        }
    }
    
    /**
     * Verify PayPal webhook signature
     * PayPal uses HMAC-SHA256 with specific headers
     */
    private boolean verifyPayPalSignature(String payload, String signature, String secret) {
        try {
            // PayPal uses HMAC-SHA256 on the payload
            String computedSig = computeHmacSha256(payload, secret);
            return MessageDigest.isEqual(
                computedSig.getBytes(StandardCharsets.UTF_8),
                signature.getBytes(StandardCharsets.UTF_8)
            );
        } catch (Exception e) {
            log.error("Error verifying PayPal signature: {}", e.getMessage(), e);
            return false;
        }
    }
    
    /**
     * Verify Razorpay webhook signature
     * Razorpay uses HMAC-SHA256
     */
    private boolean verifyRazorpaySignature(String payload, String signature, String secret) {
        try {
            String computedSig = computeHmacSha256(payload, secret);
            return MessageDigest.isEqual(
                computedSig.getBytes(StandardCharsets.UTF_8),
                signature.getBytes(StandardCharsets.UTF_8)
            );
        } catch (Exception e) {
            log.error("Error verifying Razorpay signature: {}", e.getMessage(), e);
            return false;
        }
    }
    
    /**
     * Verify Adyen webhook signature
     * Adyen uses HMAC-SHA256 with specific format
     */
    private boolean verifyAdyenSignature(String payload, String signature, String secret) {
        try {
            String computedSig = computeHmacSha256(payload, secret);
            return MessageDigest.isEqual(
                computedSig.getBytes(StandardCharsets.UTF_8),
                signature.getBytes(StandardCharsets.UTF_8)
            );
        } catch (Exception e) {
            log.error("Error verifying Adyen signature: {}", e.getMessage(), e);
            return false;
        }
    }
    
    /**
     * Generic HMAC-SHA256 verification
     */
    private boolean verifyHmacSha256(String payload, String signature, String secret) {
        try {
            String computedSig = computeHmacSha256(payload, secret);
            return MessageDigest.isEqual(
                computedSig.getBytes(StandardCharsets.UTF_8),
                signature.getBytes(StandardCharsets.UTF_8)
            );
        } catch (Exception e) {
            log.error("Error verifying HMAC-SHA256 signature: {}", e.getMessage(), e);
            return false;
        }
    }
    
    /**
     * Compute HMAC-SHA256 signature
     */
    private String computeHmacSha256(String data, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (Exception e) {
            throw new RuntimeException("Failed to compute HMAC-SHA256", e);
        }
    }
    
    /**
     * Convert bytes to hex string
     */
    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
    
    /**
     * Get webhook secret for a connector from credentials
     */
    public String getWebhookSecret(String connectorName, Map<String, String> credentials) {
        // Try common webhook secret field names
        if (credentials.containsKey("webhook_secret")) {
            return credentials.get("webhook_secret");
        }
        if (credentials.containsKey("webhookSecret")) {
            return credentials.get("webhookSecret");
        }
        if (credentials.containsKey("secret")) {
            return credentials.get("secret");
        }
        if (credentials.containsKey("api_secret")) {
            return credentials.get("api_secret");
        }
        
        // Connector-specific secret fields
        switch (connectorName.toLowerCase()) {
            case "stripe":
                return credentials.getOrDefault("webhook_signing_secret", credentials.get("api_key"));
            case "paypal":
                return credentials.getOrDefault("webhook_id", credentials.get("client_secret"));
            default:
                return credentials.get("api_key"); // Fallback
        }
    }
}


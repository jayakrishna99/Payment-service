package com.hyperswitch.common.validation;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Input validation utilities for security and data integrity
 */
public final class InputValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^(?i)[a-z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?(?:\\.[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?)+$"
    );
    
    private static final Pattern PHONE_PATTERN = Pattern.compile(
        "^\\+?[1-9]\\d{1,14}$"
    );
    
    private static final Pattern XSS_PATTERN = Pattern.compile(
        "(?i)(<script|javascript:|on\\w+\\s*=|<iframe|data:text/html)"
    );
    
    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
        "(?i)(union\\s+select|drop\\s+table|delete\\s+from|insert\\s+into|update\\s+set|exec\\s*\\()"
    );
    
    private static final int EMAIL_MAX_LENGTH = 319;
    private static final int MIN_AMOUNT_CENTS = 1;
    private static final long MAX_AMOUNT_CENTS = 999999999999L; // ~10 trillion
    
    private InputValidator() {
        // Utility class - prevent instantiation
    }

    /**
     * Validate email address format
     */
    public static ValidationResult validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return ValidationResult.error("Email address is required");
        }
        
        if (email.length() > EMAIL_MAX_LENGTH) {
            return ValidationResult.error("Email address exceeds maximum length of " + EMAIL_MAX_LENGTH + " characters");
        }
        
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return ValidationResult.error("Invalid email address format");
        }
        
        return ValidationResult.success();
    }

    /**
     * Validate phone number format (E.164 format)
     */
    public static ValidationResult validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return ValidationResult.error("Phone number is required");
        }
        
        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
            return ValidationResult.error("Invalid phone number format. Expected E.164 format (e.g., +1234567890)");
        }
        
        return ValidationResult.success();
    }

    /**
     * Validate payment amount
     */
    public static ValidationResult validateAmount(com.hyperswitch.common.types.Amount amount) {
        if (amount == null) {
            return ValidationResult.error("Amount is required");
        }
        
        if (amount.getValue() == null) {
            return ValidationResult.error("Amount value is required");
        }
        
        BigDecimal value = amount.getValue();
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            return ValidationResult.error("Amount must be greater than zero");
        }
        
        // Convert to minor units (cents) for validation
        long amountInCents = value.multiply(BigDecimal.valueOf(100)).longValue();
        
        if (amountInCents < MIN_AMOUNT_CENTS) {
            return ValidationResult.error("Amount is too small. Minimum amount is " + MIN_AMOUNT_CENTS + " cents");
        }
        
        if (amountInCents > MAX_AMOUNT_CENTS) {
            return ValidationResult.error("Amount exceeds maximum allowed value");
        }
        
        if (amount.getCurrencyCode() == null || amount.getCurrencyCode().trim().isEmpty()) {
            return ValidationResult.error("Currency code is required");
        }
        
        if (amount.getCurrencyCode().length() != 3) {
            return ValidationResult.error("Currency code must be 3 characters (ISO 4217 format)");
        }
        
        return ValidationResult.success();
    }

    /**
     * Validate currency code (ISO 4217)
     */
    public static ValidationResult validateCurrencyCode(String currencyCode) {
        if (currencyCode == null || currencyCode.trim().isEmpty()) {
            return ValidationResult.error("Currency code is required");
        }
        
        if (currencyCode.length() != 3) {
            return ValidationResult.error("Currency code must be 3 characters (ISO 4217 format)");
        }
        
        if (!currencyCode.matches("^[A-Z]{3}$")) {
            return ValidationResult.error("Currency code must be uppercase letters only");
        }
        
        return ValidationResult.success();
    }

    /**
     * Sanitize input to prevent XSS attacks
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        
        // Remove potential XSS patterns
        String sanitized = XSS_PATTERN.matcher(input).replaceAll("");
        
        // Remove HTML tags
        sanitized = sanitized.replaceAll("<[^>]*>", "");
        
        // Escape special characters
        sanitized = sanitized.replace("&", "&amp;")
                            .replace("<", "&lt;")
                            .replace(">", "&gt;")
                            .replace("\"", "&quot;")
                            .replace("'", "&#x27;");
        
        return sanitized.trim();
    }

    /**
     * Check if input contains potential XSS or SQL injection attacks
     */
    public static boolean containsSecurityThreat(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        
        String lowerInput = input.toLowerCase();
        return XSS_PATTERN.matcher(lowerInput).find() || 
               SQL_INJECTION_PATTERN.matcher(lowerInput).find();
    }

    /**
     * Validate string length
     */
    public static ValidationResult validateLength(String value, String fieldName, int minLength, int maxLength) {
        if (value == null) {
            return ValidationResult.error(fieldName + " is required");
        }
        
        int length = value.length();
        if (length < minLength) {
            return ValidationResult.error(fieldName + " must be at least " + minLength + " characters");
        }
        
        if (length > maxLength) {
            return ValidationResult.error(fieldName + " must not exceed " + maxLength + " characters");
        }
        
        return ValidationResult.success();
    }

    /**
     * Validate URL format
     */
    public static ValidationResult validateUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return ValidationResult.error("URL is required");
        }
        
        try {
            java.net.URI uri = new java.net.URI(url);
            String scheme = uri.getScheme();
            if (scheme == null || (!"http".equals(scheme) && !"https".equals(scheme))) {
                return ValidationResult.error("URL must use http or https protocol");
            }
            return ValidationResult.success();
        } catch (java.net.URISyntaxException e) {
            return ValidationResult.error("Invalid URL format: " + e.getMessage());
        }
    }

    /**
     * Validation result
     */
    public static class ValidationResult {
        private final boolean valid;
        private final String errorMessage;

        private ValidationResult(boolean valid, String errorMessage) {
            this.valid = valid;
            this.errorMessage = errorMessage;
        }

        public static ValidationResult success() {
            return new ValidationResult(true, null);
        }

        public static ValidationResult error(String message) {
            return new ValidationResult(false, message);
        }

        public boolean isValid() {
            return valid;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}


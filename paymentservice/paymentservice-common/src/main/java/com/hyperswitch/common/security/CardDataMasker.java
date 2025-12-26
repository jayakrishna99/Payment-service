package com.hyperswitch.common.security;

/**
 * Utility for masking sensitive card data for PCI compliance
 */
public final class CardDataMasker {

    private static final char MASK_CHAR = '*';
    private static final int VISIBLE_LAST_DIGITS = 4;
    private static final int MIN_CARD_LENGTH = 13;
    private static final int MAX_CARD_LENGTH = 19;

    private CardDataMasker() {
        // Utility class - prevent instantiation
    }

    /**
     * Mask card number - show only last 4 digits
     */
    public static String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            return cardNumber;
        }
        
        // Remove spaces and non-digit characters
        String cleaned = cardNumber.replaceAll("\\D", "");
        
        if (cleaned.length() < VISIBLE_LAST_DIGITS) {
            return maskString(cleaned, 0, cleaned.length());
        }
        
        // Mask all but last 4 digits
        int maskedLength = cleaned.length() - VISIBLE_LAST_DIGITS;
        String masked = maskString(cleaned, 0, maskedLength);
        String lastDigits = cleaned.substring(maskedLength);
        
        // Format with spaces for readability
        return formatCardNumber(masked + lastDigits);
    }

    /**
     * Mask CVV - show nothing
     */
    public static String maskCvv(String cvv) {
        if (cvv == null || cvv.isEmpty()) {
            return cvv;
        }
        return maskString(cvv, 0, cvv.length());
    }

    /**
     * Mask expiry date - show only year
     */
    public static String maskExpiryDate(String expiryDate) {
        if (expiryDate == null || expiryDate.isEmpty()) {
            return expiryDate;
        }
        
        if (expiryDate.contains("/")) {
            String[] parts = expiryDate.split("/");
            if (parts.length == 2) {
                return maskString(parts[0], 0, parts[0].length()) + "/" + parts[1];
            }
        }
        
        // If format is MMYY
        if (expiryDate.length() == 4) {
            return maskString(expiryDate, 0, 2) + expiryDate.substring(2);
        }
        
        return maskString(expiryDate, 0, expiryDate.length());
    }

    /**
     * Validate card number format (Luhn algorithm)
     */
    public static boolean isValidCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            return false;
        }
        
        String cleaned = cardNumber.replaceAll("\\D", "");
        
        if (cleaned.length() < MIN_CARD_LENGTH || cleaned.length() > MAX_CARD_LENGTH) {
            return false;
        }
        
        return isValidLuhn(cleaned);
    }

    /**
     * Extract last 4 digits of card number
     */
    public static String getLast4Digits(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            return "";
        }
        
        String cleaned = cardNumber.replaceAll("\\D", "");
        if (cleaned.length() < VISIBLE_LAST_DIGITS) {
            return cleaned;
        }
        
        return cleaned.substring(cleaned.length() - VISIBLE_LAST_DIGITS);
    }

    private static String maskString(String input, int start, int end) {
        if (input == null || start < 0 || end > input.length() || start > end) {
            return input;
        }
        
        StringBuilder masked = new StringBuilder(input);
        for (int i = start; i < end; i++) {
            masked.setCharAt(i, MASK_CHAR);
        }
        return masked.toString();
    }

    private static String formatCardNumber(String cardNumber) {
        // Format as **** **** **** 1111
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < cardNumber.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                formatted.append(" ");
            }
            formatted.append(cardNumber.charAt(i));
        }
        return formatted.toString();
    }

    /**
     * Luhn algorithm validation
     */
    private static boolean isValidLuhn(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));
            
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            
            sum += digit;
            alternate = !alternate;
        }
        
        return sum % 10 == 0;
    }
}

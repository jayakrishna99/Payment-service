package com.hyperswitch.common.errors;

import java.util.Set;

/**
 * Error classification for retry logic and error handling
 */
public final class ErrorClassification {

    // Hard decline errors - should not be retried
    private static final Set<String> HARD_DECLINE_ERRORS = Set.of(
        "insufficient_funds",
        "card_declined",
        "expired_card",
        "invalid_cvc",
        "invalid_card_number",
        "card_not_supported",
        "restricted_card",
        "lost_card",
        "stolen_card",
        "pickup_card",
        "fraudulent",
        "authentication_required",
        "incorrect_cvc",
        "processing_error_permanent"
    );

    // Soft decline errors - can be retried
    private static final Set<String> SOFT_DECLINE_ERRORS = Set.of(
        "processing_error",
        "timeout",
        "network_error",
        "rate_limit_exceeded",
        "temporary_failure",
        "service_unavailable",
        "gateway_timeout",
        "connection_error"
    );

    // Authentication errors - may require user action
    private static final Set<String> AUTHENTICATION_ERRORS = Set.of(
        "authentication_required",
        "requires_3ds",
        "requires_customer_action",
        "challenge_required"
    );

    private ErrorClassification() {
        // Utility class - prevent instantiation
    }

    /**
     * Check if error is a hard decline (non-retryable)
     */
    public static boolean isHardDecline(String errorCode) {
        if (errorCode == null) {
            return false;
        }
        String lowerCode = errorCode.toLowerCase();
        return HARD_DECLINE_ERRORS.contains(lowerCode) || 
               lowerCode.contains("insufficient_funds") ||
               lowerCode.contains("card_declined") ||
               lowerCode.contains("expired_card") ||
               lowerCode.contains("invalid_cvc");
    }

    /**
     * Check if error is a soft decline (retryable)
     */
    public static boolean isSoftDecline(String errorCode) {
        if (errorCode == null) {
            return false;
        }
        String lowerCode = errorCode.toLowerCase();
        return SOFT_DECLINE_ERRORS.contains(lowerCode) ||
               lowerCode.contains("timeout") ||
               lowerCode.contains("network_error") ||
               lowerCode.contains("temporary");
    }

    /**
     * Check if error requires authentication
     */
    public static boolean requiresAuthentication(String errorCode) {
        if (errorCode == null) {
            return false;
        }
        String lowerCode = errorCode.toLowerCase();
        return AUTHENTICATION_ERRORS.contains(lowerCode) ||
               lowerCode.contains("3ds") ||
               lowerCode.contains("authentication");
    }

    /**
     * Get error category
     */
    public static ErrorCategory getErrorCategory(String errorCode) {
        if (errorCode == null) {
            return ErrorCategory.UNKNOWN;
        }
        
        if (isHardDecline(errorCode)) {
            return ErrorCategory.HARD_DECLINE;
        }
        
        if (isSoftDecline(errorCode)) {
            return ErrorCategory.SOFT_DECLINE;
        }
        
        if (requiresAuthentication(errorCode)) {
            return ErrorCategory.AUTHENTICATION_REQUIRED;
        }
        
        return ErrorCategory.UNKNOWN;
    }

    /**
     * Error categories
     */
    public enum ErrorCategory {
        HARD_DECLINE,
        SOFT_DECLINE,
        AUTHENTICATION_REQUIRED,
        UNKNOWN
    }
}


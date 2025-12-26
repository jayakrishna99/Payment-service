package com.hyperswitch.common.enums;

/**
 * Payment status enumeration matching Hyperswitch payment states
 */
public enum PaymentStatus {
    /**
     * Payment requires confirmation
     */
    REQUIRES_CONFIRMATION,
    
    /**
     * Payment is being processed
     */
    PROCESSING,
    
    /**
     * Payment requires customer action (e.g., 3DS)
     */
    REQUIRES_CUSTOMER_ACTION,
    
    /**
     * Payment requires capture
     */
    REQUIRES_CAPTURE,
    
    /**
     * Payment partially captured
     */
    PARTIALLY_CAPTURED,
    
    /**
     * Payment succeeded
     */
    SUCCEEDED,
    
    /**
     * Payment failed
     */
    FAILED,
    
    /**
     * Payment cancelled
     */
    CANCELLED
}


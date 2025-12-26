package com.hyperswitch.common.enums;

/**
 * Payment capture methods
 */
public enum CaptureMethod {
    /**
     * Automatic capture - funds captured immediately
     */
    AUTOMATIC,
    
    /**
     * Manual capture - funds captured in separate step
     */
    MANUAL,
    
    /**
     * Manual multiple - funds captured in multiple partial amounts
     */
    MANUAL_MULTIPLE,
    
    /**
     * Scheduled capture - funds captured automatically at future time
     */
    SCHEDULED
}


package com.hyperswitch.common.types;

/**
 * Status of a revenue recovery payment
 */
public enum RecoveryStatus {
    RECOVERED,
    SCHEDULED,
    NO_PICKED,
    PROCESSING,
    PARTIALLY_CAPTURED_AND_PROCESSING,
    TERMINATED,
    MONITORING,
    QUEUED,
    PARTIALLY_RECOVERED
}


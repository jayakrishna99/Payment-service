package com.hyperswitch.common.types;

/**
 * Status of a fraud check
 */
public enum FraudCheckStatus {
    FRAUD,
    MANUAL_REVIEW,
    PENDING,
    LEGIT,
    TRANSACTION_FAILURE
}


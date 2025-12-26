package com.hyperswitch.common.types;

/**
 * Status of a payout
 */
public enum PayoutStatus {
    SUCCESS,
    FAILED,
    CANCELLED,
    INITIATED,
    EXPIRED,
    REVERSED,
    PENDING,
    INELIGIBLE,
    REQUIRES_CREATION,
    REQUIRES_PAYOUT_METHOD_DATA,
    REQUIRES_FULFILLMENT
}


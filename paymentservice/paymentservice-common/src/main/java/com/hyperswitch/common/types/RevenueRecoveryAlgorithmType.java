package com.hyperswitch.common.types;

/**
 * Type of revenue recovery algorithm
 */
public enum RevenueRecoveryAlgorithmType {
    EXPONENTIAL_BACKOFF,
    LINEAR_BACKOFF,
    FIXED_INTERVAL,
    ADAPTIVE,
    SMART_RETRY
}


package com.hyperswitch.common.types;

import java.util.UUID;

/**
 * Strongly typed wrapper for payout IDs
 */
public final class PayoutId {
    private final String value;

    private PayoutId(String value) {
        this.value = value;
    }

    public static PayoutId of(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("PayoutId cannot be null or blank");
        }
        return new PayoutId(value);
    }

    public static PayoutId generate() {
        return new PayoutId("payout_" + UUID.randomUUID().toString().replace("-", ""));
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayoutId payoutId = (PayoutId) o;
        return value.equals(payoutId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}


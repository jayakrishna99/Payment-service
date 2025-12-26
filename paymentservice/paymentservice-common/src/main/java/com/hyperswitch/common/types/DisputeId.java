package com.hyperswitch.common.types;

import java.util.UUID;

/**
 * Strongly typed wrapper for dispute IDs
 */
public final class DisputeId {
    private final String value;

    private DisputeId(String value) {
        this.value = value;
    }

    public static DisputeId of(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("DisputeId cannot be null or blank");
        }
        return new DisputeId(value);
    }

    public static DisputeId generate() {
        return new DisputeId("dispute_" + UUID.randomUUID().toString().replace("-", ""));
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisputeId disputeId = (DisputeId) o;
        return value.equals(disputeId.value);
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


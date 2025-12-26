package com.hyperswitch.common.types;

import java.util.UUID;

/**
 * Strongly typed wrapper for subscription IDs
 */
public final class SubscriptionId {
    private final String value;

    private SubscriptionId(String value) {
        this.value = value;
    }

    public static SubscriptionId of(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("SubscriptionId cannot be null or blank");
        }
        return new SubscriptionId(value);
    }

    public static SubscriptionId generate() {
        return new SubscriptionId("sub_" + UUID.randomUUID().toString().replace("-", ""));
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionId that = (SubscriptionId) o;
        return value.equals(that.value);
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


package com.hyperswitch.common.types;

import java.util.UUID;

/**
 * Strongly-typed wrapper for Reconciliation IDs
 */
public class ReconciliationId {
    private final String value;

    private ReconciliationId(String value) {
        this.value = value;
    }

    public static ReconciliationId generate() {
        return new ReconciliationId("recon_" + UUID.randomUUID().toString().replace("-", ""));
    }

    public static ReconciliationId of(String value) {
        return new ReconciliationId(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReconciliationId that = (ReconciliationId) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}


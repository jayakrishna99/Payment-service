package com.hyperswitch.common.types;

import java.util.Objects;
import java.util.UUID;

/**
 * Strongly typed Mandate ID
 */
public final class MandateId {
    private final String value;

    private MandateId(String value) {
        this.value = value;
    }

    public static MandateId of(String value) {
        return new MandateId(value);
    }

    public static MandateId generate() {
        return new MandateId("mandate_" + UUID.randomUUID().toString().replace("-", ""));
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
        MandateId mandateId = (MandateId) o;
        return Objects.equals(value, mandateId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}


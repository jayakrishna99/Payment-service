package com.hyperswitch.common.types;

import java.util.Objects;
import java.util.UUID;

/**
 * Strongly typed Payment ID
 */
public final class PaymentId {
    private final String value;

    private PaymentId(String value) {
        this.value = value;
    }

    public static PaymentId of(String value) {
        return new PaymentId(value);
    }

    public static PaymentId generate() {
        return new PaymentId(UUID.randomUUID().toString());
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
        PaymentId paymentId = (PaymentId) o;
        return Objects.equals(value, paymentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}


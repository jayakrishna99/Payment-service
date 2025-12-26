package com.hyperswitch.common.types;

import java.util.Objects;
import java.util.UUID;

/**
 * Strongly typed Payment Link ID
 */
public final class PaymentLinkId {
    private final String value;

    private PaymentLinkId(String value) {
        this.value = value;
    }

    public static PaymentLinkId of(String value) {
        return new PaymentLinkId(value);
    }

    public static PaymentLinkId generate() {
        return new PaymentLinkId("plink_" + UUID.randomUUID().toString().replace("-", ""));
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
        PaymentLinkId that = (PaymentLinkId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}


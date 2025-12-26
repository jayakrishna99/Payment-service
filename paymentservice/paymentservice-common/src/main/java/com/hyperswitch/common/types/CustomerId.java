package com.hyperswitch.common.types;

import java.util.Objects;
import java.util.UUID;

/**
 * Strongly typed Customer ID
 */
public final class CustomerId {
    private final String value;

    private CustomerId(String value) {
        this.value = value;
    }

    public static CustomerId of(String value) {
        return new CustomerId(value);
    }

    public static CustomerId generate() {
        return new CustomerId(UUID.randomUUID().toString());
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
        CustomerId that = (CustomerId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}


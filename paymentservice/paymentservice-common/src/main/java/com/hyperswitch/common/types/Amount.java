package com.hyperswitch.common.types;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * Monetary amount with currency
 */
public final class Amount {
    private final BigDecimal value;
    private final Currency currency;

    public Amount(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public static Amount of(BigDecimal value, Currency currency) {
        return new Amount(value, currency);
    }

    public static Amount of(BigDecimal value, String currencyCode) {
        return new Amount(value, Currency.getInstance(currencyCode));
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getCurrencyCode() {
        return currency.getCurrencyCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value) && Objects.equals(currency, amount.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return value + " " + currency.getCurrencyCode();
    }
}


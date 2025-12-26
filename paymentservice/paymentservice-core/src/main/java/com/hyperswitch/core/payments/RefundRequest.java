package com.hyperswitch.core.payments;

import com.hyperswitch.common.types.Amount;

/**
 * Request to process a refund
 */
public final class RefundRequest {
    private final Amount amount; // null means full refund
    private final String reason;

    private RefundRequest(Builder builder) {
        this.amount = builder.amount;
        this.reason = builder.reason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Amount getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    public static class Builder {
        private Amount amount;
        private String reason;

        public Builder amount(Amount amount) {
            this.amount = amount;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public RefundRequest build() {
            return new RefundRequest(this);
        }
    }
}

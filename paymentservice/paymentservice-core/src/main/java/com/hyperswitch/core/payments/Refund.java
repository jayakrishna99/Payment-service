package com.hyperswitch.core.payments;

import com.hyperswitch.common.types.Amount;

import java.time.Instant;

/**
 * Refund entity
 */
public final class Refund {
    private final String refundId;
    private final String paymentId;
    private final Amount amount;
    private final String status;
    private final String reason;
    private final Instant createdAt;
    private final String connectorRefundId;

    private Refund(Builder builder) {
        this.refundId = builder.refundId;
        this.paymentId = builder.paymentId;
        this.amount = builder.amount;
        this.status = builder.status;
        this.reason = builder.reason;
        this.createdAt = builder.createdAt;
        this.connectorRefundId = builder.connectorRefundId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getRefundId() {
        return refundId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Amount getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getConnectorRefundId() {
        return connectorRefundId;
    }

    public static class Builder {
        private String refundId;
        private String paymentId;
        private Amount amount;
        private String status;
        private String reason;
        private Instant createdAt;
        private String connectorRefundId;

        public Builder refundId(String refundId) {
            this.refundId = refundId;
            return this;
        }

        public Builder paymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder amount(Amount amount) {
            this.amount = amount;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder connectorRefundId(String connectorRefundId) {
            this.connectorRefundId = connectorRefundId;
            return this;
        }

        public Refund build() {
            return new Refund(this);
        }
    }
}

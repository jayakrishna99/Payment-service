package com.hyperswitch.core.payments;

import com.hyperswitch.common.enums.PaymentMethod;
import com.hyperswitch.common.enums.PaymentStatus;
import com.hyperswitch.common.types.Amount;
import com.hyperswitch.common.types.PaymentId;

import java.time.Instant;
import java.util.Map;

/**
 * Payment intent entity representing a payment transaction
 */
public final class PaymentIntent {
    private final PaymentId paymentId;
    private final Amount amount;
    private final PaymentMethod paymentMethod;
    private final PaymentStatus status;
    private final String merchantId;
    private final String customerId;
    private final Instant createdAt;
    private final Instant updatedAt;
    private final Map<String, Object> metadata;
    private final String connectorName;
    private final String connectorTransactionId;
    private final String errorMessage;
    private final String errorCode;

    private PaymentIntent(Builder builder) {
        this.paymentId = builder.paymentId;
        this.amount = builder.amount;
        this.paymentMethod = builder.paymentMethod;
        this.status = builder.status;
        this.merchantId = builder.merchantId;
        this.customerId = builder.customerId;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.metadata = builder.metadata;
        this.connectorName = builder.connectorName;
        this.connectorTransactionId = builder.connectorTransactionId;
        this.errorMessage = builder.errorMessage;
        this.errorCode = builder.errorCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PaymentId getPaymentId() {
        return paymentId;
    }

    public Amount getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public String getConnectorName() {
        return connectorName;
    }

    public String getConnectorTransactionId() {
        return connectorTransactionId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public static class Builder {
        private PaymentId paymentId;
        private Amount amount;
        private PaymentMethod paymentMethod;
        private PaymentStatus status;
        private String merchantId;
        private String customerId;
        private Instant createdAt;
        private Instant updatedAt;
        private Map<String, Object> metadata;
        private String connectorName;
        private String connectorTransactionId;
        private String errorMessage;
        private String errorCode;

        public Builder paymentId(PaymentId paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder amount(Amount amount) {
            this.amount = amount;
            return this;
        }

        public Builder paymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder status(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public Builder merchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder connectorName(String connectorName) {
            this.connectorName = connectorName;
            return this;
        }

        public Builder connectorTransactionId(String connectorTransactionId) {
            this.connectorTransactionId = connectorTransactionId;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public PaymentIntent build() {
            return new PaymentIntent(this);
        }
    }
}

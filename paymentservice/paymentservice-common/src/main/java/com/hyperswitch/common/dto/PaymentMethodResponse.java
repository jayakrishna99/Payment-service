package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.CustomerId;
import com.hyperswitch.common.types.MerchantId;
import com.hyperswitch.common.types.PaymentMethodId;

import java.time.Instant;
import java.util.Map;

/**
 * Response DTO for payment method operations
 */
public final class PaymentMethodResponse {
    private final PaymentMethodId paymentMethodId;
    private final CustomerId customerId;
    private final MerchantId merchantId;
    private final String paymentMethodType;
    private final String paymentMethodSubtype;
    private final Map<String, Object> paymentMethodData;
    private final String lockerId;
    private final Instant lastUsedAt;
    private final String status;
    private final Map<String, Object> connectorMandateDetails;
    private final String networkTransactionId;
    private final String clientSecret;
    private final Instant createdAt;
    private final Instant modifiedAt;

    private PaymentMethodResponse(Builder builder) {
        this.paymentMethodId = builder.paymentMethodId;
        this.customerId = builder.customerId;
        this.merchantId = builder.merchantId;
        this.paymentMethodType = builder.paymentMethodType;
        this.paymentMethodSubtype = builder.paymentMethodSubtype;
        this.paymentMethodData = builder.paymentMethodData;
        this.lockerId = builder.lockerId;
        this.lastUsedAt = builder.lastUsedAt;
        this.status = builder.status;
        this.connectorMandateDetails = builder.connectorMandateDetails;
        this.networkTransactionId = builder.networkTransactionId;
        this.clientSecret = builder.clientSecret;
        this.createdAt = builder.createdAt;
        this.modifiedAt = builder.modifiedAt;
    }

    public PaymentMethodId getPaymentMethodId() {
        return paymentMethodId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public MerchantId getMerchantId() {
        return merchantId;
    }

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public String getPaymentMethodSubtype() {
        return paymentMethodSubtype;
    }

    public Map<String, Object> getPaymentMethodData() {
        return paymentMethodData;
    }

    public String getLockerId() {
        return lockerId;
    }

    public Instant getLastUsedAt() {
        return lastUsedAt;
    }

    public String getStatus() {
        return status;
    }

    public Map<String, Object> getConnectorMandateDetails() {
        return connectorMandateDetails;
    }

    public String getNetworkTransactionId() {
        return networkTransactionId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PaymentMethodId paymentMethodId;
        private CustomerId customerId;
        private MerchantId merchantId;
        private String paymentMethodType;
        private String paymentMethodSubtype;
        private Map<String, Object> paymentMethodData;
        private String lockerId;
        private Instant lastUsedAt;
        private String status;
        private Map<String, Object> connectorMandateDetails;
        private String networkTransactionId;
        private String clientSecret;
        private Instant createdAt;
        private Instant modifiedAt;

        public Builder paymentMethodId(PaymentMethodId paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
        }

        public Builder customerId(CustomerId customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder merchantId(MerchantId merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder paymentMethodType(String paymentMethodType) {
            this.paymentMethodType = paymentMethodType;
            return this;
        }

        public Builder paymentMethodSubtype(String paymentMethodSubtype) {
            this.paymentMethodSubtype = paymentMethodSubtype;
            return this;
        }

        public Builder paymentMethodData(Map<String, Object> paymentMethodData) {
            this.paymentMethodData = paymentMethodData;
            return this;
        }

        public Builder lockerId(String lockerId) {
            this.lockerId = lockerId;
            return this;
        }

        public Builder lastUsedAt(Instant lastUsedAt) {
            this.lastUsedAt = lastUsedAt;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder connectorMandateDetails(Map<String, Object> connectorMandateDetails) {
            this.connectorMandateDetails = connectorMandateDetails;
            return this;
        }

        public Builder networkTransactionId(String networkTransactionId) {
            this.networkTransactionId = networkTransactionId;
            return this;
        }

        public Builder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder modifiedAt(Instant modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public PaymentMethodResponse build() {
            return new PaymentMethodResponse(this);
        }
    }
}


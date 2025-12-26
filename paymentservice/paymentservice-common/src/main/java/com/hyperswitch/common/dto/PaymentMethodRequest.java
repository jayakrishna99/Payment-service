package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.CustomerId;
import com.hyperswitch.common.types.MerchantId;

import java.util.Map;

/**
 * Request DTO for creating a payment method
 */
public final class PaymentMethodRequest {
    private final MerchantId merchantId;
    private final CustomerId customerId;
    private final String paymentMethodType;
    private final String paymentMethodSubtype;
    private final Map<String, Object> paymentMethodData;
    private final String lockerId;
    private final Map<String, Object> connectorMandateDetails;
    private final String networkTransactionId;
    private final String clientSecret;

    private PaymentMethodRequest(Builder builder) {
        this.merchantId = builder.merchantId;
        this.customerId = builder.customerId;
        this.paymentMethodType = builder.paymentMethodType;
        this.paymentMethodSubtype = builder.paymentMethodSubtype;
        this.paymentMethodData = builder.paymentMethodData;
        this.lockerId = builder.lockerId;
        this.connectorMandateDetails = builder.connectorMandateDetails;
        this.networkTransactionId = builder.networkTransactionId;
        this.clientSecret = builder.clientSecret;
    }

    public MerchantId getMerchantId() {
        return merchantId;
    }

    public CustomerId getCustomerId() {
        return customerId;
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

    public Map<String, Object> getConnectorMandateDetails() {
        return connectorMandateDetails;
    }

    public String getNetworkTransactionId() {
        return networkTransactionId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MerchantId merchantId;
        private CustomerId customerId;
        private String paymentMethodType;
        private String paymentMethodSubtype;
        private Map<String, Object> paymentMethodData;
        private String lockerId;
        private Map<String, Object> connectorMandateDetails;
        private String networkTransactionId;
        private String clientSecret;

        public Builder merchantId(MerchantId merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder customerId(CustomerId customerId) {
            this.customerId = customerId;
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

        public PaymentMethodRequest build() {
            return new PaymentMethodRequest(this);
        }
    }
}


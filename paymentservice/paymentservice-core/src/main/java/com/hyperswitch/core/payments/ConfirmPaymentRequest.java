package com.hyperswitch.core.payments;

import com.hyperswitch.common.enums.PaymentMethod;

import java.util.Map;

/**
 * Request to confirm/process a payment
 */
public final class ConfirmPaymentRequest {
    private final PaymentMethod paymentMethod;
    private final Map<String, Object> paymentMethodData;
    private final String returnUrl;
    private final Map<String, Object> metadata;
    private final Boolean offSession; // For MIT payments
    private final com.hyperswitch.common.dto.RecurringDetails recurringDetails; // For MIT payments

    private ConfirmPaymentRequest(Builder builder) {
        this.paymentMethod = builder.paymentMethod;
        this.paymentMethodData = builder.paymentMethodData;
        this.returnUrl = builder.returnUrl;
        this.metadata = builder.metadata;
        this.offSession = builder.offSession;
        this.recurringDetails = builder.recurringDetails;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Map<String, Object> getPaymentMethodData() {
        return paymentMethodData;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public Boolean getOffSession() {
        return offSession;
    }

    public com.hyperswitch.common.dto.RecurringDetails getRecurringDetails() {
        return recurringDetails;
    }

    public static class Builder {
        private PaymentMethod paymentMethod;
        private Map<String, Object> paymentMethodData;
        private String returnUrl;
        private Map<String, Object> metadata;
        private Boolean offSession;
        private com.hyperswitch.common.dto.RecurringDetails recurringDetails;

        public Builder paymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder paymentMethodData(Map<String, Object> paymentMethodData) {
            this.paymentMethodData = paymentMethodData;
            return this;
        }

        public Builder returnUrl(String returnUrl) {
            this.returnUrl = returnUrl;
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder offSession(Boolean offSession) {
            this.offSession = offSession;
            return this;
        }

        public Builder recurringDetails(com.hyperswitch.common.dto.RecurringDetails recurringDetails) {
            this.recurringDetails = recurringDetails;
            return this;
        }

        public ConfirmPaymentRequest build() {
            return new ConfirmPaymentRequest(this);
        }
    }
}

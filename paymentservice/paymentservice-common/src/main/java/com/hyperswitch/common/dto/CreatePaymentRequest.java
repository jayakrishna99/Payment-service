package com.hyperswitch.common.dto;

import com.hyperswitch.common.enums.CaptureMethod;
import com.hyperswitch.common.enums.PaymentMethod;
import com.hyperswitch.common.types.Amount;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

/**
 * Request to create a new payment
 */
public final class CreatePaymentRequest {
    @NotNull
    private final Amount amount;
    
    @NotNull
    private final String merchantId;
    
    private final PaymentMethod paymentMethod;
    private final String customerId;
    private final CaptureMethod captureMethod;
    private final Boolean confirm;
    private final String returnUrl;
    private final Map<String, Object> metadata;
    private final String description;
    private final String authenticationType;
    private final Boolean offSession; // For MIT payments
    private final RecurringDetails recurringDetails; // For MIT payments
    private final String paymentType; // "setup_mandate" for zero-dollar authorization

    private CreatePaymentRequest(Builder builder) {
        this.amount = builder.amount;
        this.merchantId = builder.merchantId;
        this.paymentMethod = builder.paymentMethod;
        this.customerId = builder.customerId;
        this.captureMethod = builder.captureMethod;
        this.confirm = builder.confirm;
        this.returnUrl = builder.returnUrl;
        this.metadata = builder.metadata;
        this.description = builder.description;
        this.authenticationType = builder.authenticationType;
        this.offSession = builder.offSession;
        this.recurringDetails = builder.recurringDetails;
        this.paymentType = builder.paymentType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Amount getAmount() {
        return amount;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getCustomerId() {
        return customerId;
    }

    public CaptureMethod getCaptureMethod() {
        return captureMethod;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public Boolean getOffSession() {
        return offSession;
    }

    public RecurringDetails getRecurringDetails() {
        return recurringDetails;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public static class Builder {
        private Amount amount;
        private String merchantId;
        private PaymentMethod paymentMethod;
        private String customerId;
        private CaptureMethod captureMethod;
        private Boolean confirm;
        private String returnUrl;
        private Map<String, Object> metadata;
        private String description;
        private String authenticationType;
        private Boolean offSession;
        private RecurringDetails recurringDetails;
        private String paymentType;

        public Builder amount(Amount amount) {
            this.amount = amount;
            return this;
        }

        public Builder merchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder paymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder captureMethod(CaptureMethod captureMethod) {
            this.captureMethod = captureMethod;
            return this;
        }

        public Builder confirm(Boolean confirm) {
            this.confirm = confirm;
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

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder authenticationType(String authenticationType) {
            this.authenticationType = authenticationType;
            return this;
        }

        public Builder offSession(Boolean offSession) {
            this.offSession = offSession;
            return this;
        }

        public Builder recurringDetails(RecurringDetails recurringDetails) {
            this.recurringDetails = recurringDetails;
            return this;
        }

        public Builder paymentType(String paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public CreatePaymentRequest build() {
            return new CreatePaymentRequest(this);
        }
    }
}


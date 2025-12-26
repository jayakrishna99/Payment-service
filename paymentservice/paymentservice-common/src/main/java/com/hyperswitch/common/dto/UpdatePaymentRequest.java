package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.Amount;
import java.util.Map;

/**
 * Request DTO for updating a payment
 */
public final class UpdatePaymentRequest {
    private final Amount amount;
    private final String description;
    private final Map<String, Object> metadata;
    private final String returnUrl;

    private UpdatePaymentRequest(Builder builder) {
        this.amount = builder.amount;
        this.description = builder.description;
        this.metadata = builder.metadata;
        this.returnUrl = builder.returnUrl;
    }

    public Amount getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Amount amount;
        private String description;
        private Map<String, Object> metadata;
        private String returnUrl;

        public Builder amount(Amount amount) {
            this.amount = amount;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder returnUrl(String returnUrl) {
            this.returnUrl = returnUrl;
            return this;
        }

        public UpdatePaymentRequest build() {
            return new UpdatePaymentRequest(this);
        }
    }
}


package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Request DTO for cancelling a payment
 */
public final class CancelPaymentRequest {
    private final String cancellationReason;
    private final Map<String, Object> metadata;

    private CancelPaymentRequest(Builder builder) {
        this.cancellationReason = builder.cancellationReason;
        this.metadata = builder.metadata;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String cancellationReason;
        private Map<String, Object> metadata;

        public Builder cancellationReason(String cancellationReason) {
            this.cancellationReason = cancellationReason;
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public CancelPaymentRequest build() {
            return new CancelPaymentRequest(this);
        }
    }
}


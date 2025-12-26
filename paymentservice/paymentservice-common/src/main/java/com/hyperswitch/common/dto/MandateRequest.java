package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.Amount;
import com.hyperswitch.common.types.MandateType;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Map;

/**
 * Request to create a mandate
 */
public final class MandateRequest {
    @NotNull
    private final String customerId;
    
    @NotNull
    private final String paymentMethodId;
    
    @NotNull
    private final MandateType mandateType;
    
    private final Amount mandateAmount; // For single_use mandates
    private final Instant startDate;
    private final Instant endDate;
    private final Map<String, Object> metadata;

    private MandateRequest(Builder builder) {
        this.customerId = builder.customerId;
        this.paymentMethodId = builder.paymentMethodId;
        this.mandateType = builder.mandateType;
        this.mandateAmount = builder.mandateAmount;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.metadata = builder.metadata;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public MandateType getMandateType() {
        return mandateType;
    }

    public Amount getMandateAmount() {
        return mandateAmount;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public static class Builder {
        private String customerId;
        private String paymentMethodId;
        private MandateType mandateType;
        private Amount mandateAmount;
        private Instant startDate;
        private Instant endDate;
        private Map<String, Object> metadata;

        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder paymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
        }

        public Builder mandateType(MandateType mandateType) {
            this.mandateType = mandateType;
            return this;
        }

        public Builder mandateAmount(Amount mandateAmount) {
            this.mandateAmount = mandateAmount;
            return this;
        }

        public Builder startDate(Instant startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Instant endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public MandateRequest build() {
            return new MandateRequest(this);
        }
    }
}


package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.MandateStatus;
import com.hyperswitch.common.types.MandateType;

import java.time.Instant;
import java.util.Map;

/**
 * Mandate response DTO
 */
public final class MandateResponse {
    private final String mandateId;
    private final String customerId;
    private final String paymentMethodId;
    private final MandateStatus status;
    private final MandateType type;
    private final Instant customerAcceptedAt;
    private final Long mandateAmount;
    private final String mandateCurrency;
    private final Instant startDate;
    private final Instant endDate;
    private final String connectorMandateId;
    private final Map<String, Object> metadata;
    private final Instant createdAt;

    private MandateResponse(Builder builder) {
        this.mandateId = builder.mandateId;
        this.customerId = builder.customerId;
        this.paymentMethodId = builder.paymentMethodId;
        this.status = builder.status;
        this.type = builder.type;
        this.customerAcceptedAt = builder.customerAcceptedAt;
        this.mandateAmount = builder.mandateAmount;
        this.mandateCurrency = builder.mandateCurrency;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.connectorMandateId = builder.connectorMandateId;
        this.metadata = builder.metadata;
        this.createdAt = builder.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getMandateId() {
        return mandateId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public MandateStatus getStatus() {
        return status;
    }

    public MandateType getType() {
        return type;
    }

    public Instant getCustomerAcceptedAt() {
        return customerAcceptedAt;
    }

    public Long getMandateAmount() {
        return mandateAmount;
    }

    public String getMandateCurrency() {
        return mandateCurrency;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public String getConnectorMandateId() {
        return connectorMandateId;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public static class Builder {
        private String mandateId;
        private String customerId;
        private String paymentMethodId;
        private MandateStatus status;
        private MandateType type;
        private Instant customerAcceptedAt;
        private Long mandateAmount;
        private String mandateCurrency;
        private Instant startDate;
        private Instant endDate;
        private String connectorMandateId;
        private Map<String, Object> metadata;
        private Instant createdAt;

        public Builder mandateId(String mandateId) {
            this.mandateId = mandateId;
            return this;
        }

        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder paymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
        }

        public Builder status(MandateStatus status) {
            this.status = status;
            return this;
        }

        public Builder type(MandateType type) {
            this.type = type;
            return this;
        }

        public Builder customerAcceptedAt(Instant customerAcceptedAt) {
            this.customerAcceptedAt = customerAcceptedAt;
            return this;
        }

        public Builder mandateAmount(Long mandateAmount) {
            this.mandateAmount = mandateAmount;
            return this;
        }

        public Builder mandateCurrency(String mandateCurrency) {
            this.mandateCurrency = mandateCurrency;
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

        public Builder connectorMandateId(String connectorMandateId) {
            this.connectorMandateId = connectorMandateId;
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public MandateResponse build() {
            return new MandateResponse(this);
        }
    }
}


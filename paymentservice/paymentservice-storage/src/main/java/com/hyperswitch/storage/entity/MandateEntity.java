package com.hyperswitch.storage.entity;

import com.hyperswitch.common.types.MandateStatus;
import com.hyperswitch.common.types.MandateType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Map;

/**
 * Mandate entity matching Hyperswitch schema
 */
@Table("mandate")
public class MandateEntity {
    @Id
    @Column("mandate_id")
    private String mandateId;
    
    @Column("customer_id")
    private String customerId;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("payment_method_id")
    private String paymentMethodId;
    
    @Column("mandate_status")
    private MandateStatus mandateStatus;
    
    @Column("mandate_type")
    private MandateType mandateType;
    
    @Column("customer_accepted_at")
    private Instant customerAcceptedAt;
    
    @Column("customer_ip_address")
    private String customerIpAddress;
    
    @Column("customer_user_agent")
    private String customerUserAgent;
    
    @Column("network_transaction_id")
    private String networkTransactionId;
    
    @Column("previous_attempt_id")
    private String previousAttemptId;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("mandate_amount")
    private Long mandateAmount; // Stored in minor units (cents)
    
    @Column("mandate_currency")
    private String mandateCurrency;
    
    @Column("amount_captured")
    private Long amountCaptured; // Stored in minor units (cents)
    
    @Column("connector")
    private String connector;
    
    @Column("connector_mandate_id")
    private String connectorMandateId;
    
    @Column("start_date")
    private Instant startDate;
    
    @Column("end_date")
    private Instant endDate;
    
    @Column("metadata")
    private Map<String, Object> metadata;
    
    @Column("connector_mandate_ids")
    private Map<String, Object> connectorMandateIds;
    
    @Column("original_payment_id")
    private String originalPaymentId;
    
    @Column("merchant_connector_id")
    private String merchantConnectorId;
    
    @Column("updated_by")
    private String updatedBy;
    
    @Column("customer_user_agent_extended")
    private String customerUserAgentExtended;

    // Getters and Setters
    public String getMandateId() {
        return mandateId;
    }

    public void setMandateId(String mandateId) {
        this.mandateId = mandateId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public MandateStatus getMandateStatus() {
        return mandateStatus;
    }

    public void setMandateStatus(MandateStatus mandateStatus) {
        this.mandateStatus = mandateStatus;
    }

    public MandateType getMandateType() {
        return mandateType;
    }

    public void setMandateType(MandateType mandateType) {
        this.mandateType = mandateType;
    }

    public Instant getCustomerAcceptedAt() {
        return customerAcceptedAt;
    }

    public void setCustomerAcceptedAt(Instant customerAcceptedAt) {
        this.customerAcceptedAt = customerAcceptedAt;
    }

    public String getCustomerIpAddress() {
        return customerIpAddress;
    }

    public void setCustomerIpAddress(String customerIpAddress) {
        this.customerIpAddress = customerIpAddress;
    }

    public String getCustomerUserAgent() {
        return customerUserAgent;
    }

    public void setCustomerUserAgent(String customerUserAgent) {
        this.customerUserAgent = customerUserAgent;
    }

    public String getNetworkTransactionId() {
        return networkTransactionId;
    }

    public void setNetworkTransactionId(String networkTransactionId) {
        this.networkTransactionId = networkTransactionId;
    }

    public String getPreviousAttemptId() {
        return previousAttemptId;
    }

    public void setPreviousAttemptId(String previousAttemptId) {
        this.previousAttemptId = previousAttemptId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getMandateAmount() {
        return mandateAmount;
    }

    public void setMandateAmount(Long mandateAmount) {
        this.mandateAmount = mandateAmount;
    }

    public String getMandateCurrency() {
        return mandateCurrency;
    }

    public void setMandateCurrency(String mandateCurrency) {
        this.mandateCurrency = mandateCurrency;
    }

    public Long getAmountCaptured() {
        return amountCaptured;
    }

    public void setAmountCaptured(Long amountCaptured) {
        this.amountCaptured = amountCaptured;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getConnectorMandateId() {
        return connectorMandateId;
    }

    public void setConnectorMandateId(String connectorMandateId) {
        this.connectorMandateId = connectorMandateId;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public Map<String, Object> getConnectorMandateIds() {
        return connectorMandateIds;
    }

    public void setConnectorMandateIds(Map<String, Object> connectorMandateIds) {
        this.connectorMandateIds = connectorMandateIds;
    }

    public String getOriginalPaymentId() {
        return originalPaymentId;
    }

    public void setOriginalPaymentId(String originalPaymentId) {
        this.originalPaymentId = originalPaymentId;
    }

    public String getMerchantConnectorId() {
        return merchantConnectorId;
    }

    public void setMerchantConnectorId(String merchantConnectorId) {
        this.merchantConnectorId = merchantConnectorId;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCustomerUserAgentExtended() {
        return customerUserAgentExtended;
    }

    public void setCustomerUserAgentExtended(String customerUserAgentExtended) {
        this.customerUserAgentExtended = customerUserAgentExtended;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MandateEntity entity = new MandateEntity();

        public Builder mandateId(String mandateId) {
            entity.mandateId = mandateId;
            return this;
        }

        public Builder customerId(String customerId) {
            entity.customerId = customerId;
            return this;
        }

        public Builder merchantId(String merchantId) {
            entity.merchantId = merchantId;
            return this;
        }

        public Builder paymentMethodId(String paymentMethodId) {
            entity.paymentMethodId = paymentMethodId;
            return this;
        }

        public Builder mandateStatus(MandateStatus mandateStatus) {
            entity.mandateStatus = mandateStatus;
            return this;
        }

        public Builder mandateType(MandateType mandateType) {
            entity.mandateType = mandateType;
            return this;
        }

        public Builder customerAcceptedAt(Instant customerAcceptedAt) {
            entity.customerAcceptedAt = customerAcceptedAt;
            return this;
        }

        public Builder customerIpAddress(String customerIpAddress) {
            entity.customerIpAddress = customerIpAddress;
            return this;
        }

        public Builder customerUserAgent(String customerUserAgent) {
            entity.customerUserAgent = customerUserAgent;
            return this;
        }

        public Builder networkTransactionId(String networkTransactionId) {
            entity.networkTransactionId = networkTransactionId;
            return this;
        }

        public Builder previousAttemptId(String previousAttemptId) {
            entity.previousAttemptId = previousAttemptId;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            entity.createdAt = createdAt;
            return this;
        }

        public Builder mandateAmount(Long mandateAmount) {
            entity.mandateAmount = mandateAmount;
            return this;
        }

        public Builder mandateCurrency(String mandateCurrency) {
            entity.mandateCurrency = mandateCurrency;
            return this;
        }

        public Builder amountCaptured(Long amountCaptured) {
            entity.amountCaptured = amountCaptured;
            return this;
        }

        public Builder connector(String connector) {
            entity.connector = connector;
            return this;
        }

        public Builder connectorMandateId(String connectorMandateId) {
            entity.connectorMandateId = connectorMandateId;
            return this;
        }

        public Builder startDate(Instant startDate) {
            entity.startDate = startDate;
            return this;
        }

        public Builder endDate(Instant endDate) {
            entity.endDate = endDate;
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            entity.metadata = metadata;
            return this;
        }

        public Builder connectorMandateIds(Map<String, Object> connectorMandateIds) {
            entity.connectorMandateIds = connectorMandateIds;
            return this;
        }

        public Builder originalPaymentId(String originalPaymentId) {
            entity.originalPaymentId = originalPaymentId;
            return this;
        }

        public Builder merchantConnectorId(String merchantConnectorId) {
            entity.merchantConnectorId = merchantConnectorId;
            return this;
        }

        public Builder updatedBy(String updatedBy) {
            entity.updatedBy = updatedBy;
            return this;
        }

        public Builder customerUserAgentExtended(String customerUserAgentExtended) {
            entity.customerUserAgentExtended = customerUserAgentExtended;
            return this;
        }

        public MandateEntity build() {
            return entity;
        }
    }
}


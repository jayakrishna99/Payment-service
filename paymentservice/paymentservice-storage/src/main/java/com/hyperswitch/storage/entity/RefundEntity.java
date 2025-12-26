package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Map;

/**
 * Refund entity
 */
@Table("refund")
public class RefundEntity {
    @Id
    @Column("id")
    private String id;
    
    @Column("refund_id")
    private String refundId;
    
    @Column("payment_id")
    private String paymentId;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("connector_transaction_id")
    private String connectorTransactionId;
    
    @Column("connector")
    private String connector;
    
    @Column("connector_refund_id")
    private String connectorRefundId;
    
    @Column("refund_type")
    private String refundType;
    
    @Column("total_amount")
    private Long totalAmount;
    
    @Column("currency")
    private String currency;
    
    @Column("refund_amount")
    private Long refundAmount;
    
    @Column("refund_status")
    private String refundStatus;
    
    @Column("sent_to_gateway")
    private Boolean sentToGateway;
    
    @Column("refund_error_message")
    private String refundErrorMessage;
    
    @Column("refund_error_code")
    private String refundErrorCode;
    
    @Column("metadata")
    private Map<String, Object> metadata;
    
    @Column("refund_reason")
    private String refundReason;
    
    @Column("attempt_id")
    private String attemptId;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("modified_at")
    private Instant modifiedAt;
    
    @Column("profile_id")
    private String profileId;
    
    @Column("organization_id")
    private String organizationId;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getConnectorTransactionId() {
        return connectorTransactionId;
    }

    public void setConnectorTransactionId(String connectorTransactionId) {
        this.connectorTransactionId = connectorTransactionId;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getConnectorRefundId() {
        return connectorRefundId;
    }

    public void setConnectorRefundId(String connectorRefundId) {
        this.connectorRefundId = connectorRefundId;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Boolean getSentToGateway() {
        return sentToGateway;
    }

    public void setSentToGateway(Boolean sentToGateway) {
        this.sentToGateway = sentToGateway;
    }

    public String getRefundErrorMessage() {
        return refundErrorMessage;
    }

    public void setRefundErrorMessage(String refundErrorMessage) {
        this.refundErrorMessage = refundErrorMessage;
    }

    public String getRefundErrorCode() {
        return refundErrorCode;
    }

    public void setRefundErrorCode(String refundErrorCode) {
        this.refundErrorCode = refundErrorCode;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(String attemptId) {
        this.attemptId = attemptId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private RefundEntity entity = new RefundEntity();

        public Builder id(String id) {
            entity.id = id;
            return this;
        }

        public Builder refundId(String refundId) {
            entity.refundId = refundId;
            return this;
        }

        public Builder paymentId(String paymentId) {
            entity.paymentId = paymentId;
            return this;
        }

        public Builder merchantId(String merchantId) {
            entity.merchantId = merchantId;
            return this;
        }

        public Builder connectorTransactionId(String connectorTransactionId) {
            entity.connectorTransactionId = connectorTransactionId;
            return this;
        }

        public Builder connector(String connector) {
            entity.connector = connector;
            return this;
        }

        public Builder connectorRefundId(String connectorRefundId) {
            entity.connectorRefundId = connectorRefundId;
            return this;
        }

        public Builder refundType(String refundType) {
            entity.refundType = refundType;
            return this;
        }

        public Builder totalAmount(Long totalAmount) {
            entity.totalAmount = totalAmount;
            return this;
        }

        public Builder currency(String currency) {
            entity.currency = currency;
            return this;
        }

        public Builder refundAmount(Long refundAmount) {
            entity.refundAmount = refundAmount;
            return this;
        }

        public Builder refundStatus(String refundStatus) {
            entity.refundStatus = refundStatus;
            return this;
        }

        public Builder sentToGateway(Boolean sentToGateway) {
            entity.sentToGateway = sentToGateway;
            return this;
        }

        public Builder refundErrorMessage(String refundErrorMessage) {
            entity.refundErrorMessage = refundErrorMessage;
            return this;
        }

        public Builder refundErrorCode(String refundErrorCode) {
            entity.refundErrorCode = refundErrorCode;
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            entity.metadata = metadata;
            return this;
        }

        public Builder refundReason(String refundReason) {
            entity.refundReason = refundReason;
            return this;
        }

        public Builder attemptId(String attemptId) {
            entity.attemptId = attemptId;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            entity.createdAt = createdAt;
            return this;
        }

        public Builder modifiedAt(Instant modifiedAt) {
            entity.modifiedAt = modifiedAt;
            return this;
        }

        public Builder profileId(String profileId) {
            entity.profileId = profileId;
            return this;
        }

        public Builder organizationId(String organizationId) {
            entity.organizationId = organizationId;
            return this;
        }

        public RefundEntity build() {
            return entity;
        }
    }
}

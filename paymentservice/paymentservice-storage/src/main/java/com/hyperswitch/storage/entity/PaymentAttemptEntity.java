package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Map;

/**
 * Payment Attempt entity - tracks each attempt to process a payment
 */
@Table("payment_attempt")
public class PaymentAttemptEntity {
    @Id
    @Column("id")
    private String id;
    
    @Column("payment_id")
    private String paymentId;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("status")
    private String status; // AttemptStatus enum
    
    @Column("connector")
    private String connector;
    
    @Column("error_message")
    private String errorMessage;
    
    @Column("error_code")
    private String errorCode;
    
    @Column("payment_method_id")
    private String paymentMethodId;
    
    @Column("authentication_type")
    private String authenticationType;
    
    @Column("connector_transaction_id")
    private String connectorTransactionId;
    
    @Column("amount_to_capture")
    private Long amountToCapture;
    
    @Column("amount_capturable")
    private Long amountCapturable;
    
    @Column("amount_captured")
    private Long amountCaptured;
    
    @Column("browser_info")
    private Map<String, Object> browserInfo;
    
    @Column("connector_metadata")
    private Map<String, Object> connectorMetadata;
    
    @Column("payment_method_data")
    private Map<String, Object> paymentMethodData;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("modified_at")
    private Instant modifiedAt;
    
    @Column("last_synced")
    private Instant lastSynced;
    
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }

    public String getConnectorTransactionId() {
        return connectorTransactionId;
    }

    public void setConnectorTransactionId(String connectorTransactionId) {
        this.connectorTransactionId = connectorTransactionId;
    }

    public Long getAmountToCapture() {
        return amountToCapture;
    }

    public void setAmountToCapture(Long amountToCapture) {
        this.amountToCapture = amountToCapture;
    }

    public Long getAmountCapturable() {
        return amountCapturable;
    }

    public void setAmountCapturable(Long amountCapturable) {
        this.amountCapturable = amountCapturable;
    }

    public Long getAmountCaptured() {
        return amountCaptured;
    }

    public void setAmountCaptured(Long amountCaptured) {
        this.amountCaptured = amountCaptured;
    }

    public Map<String, Object> getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(Map<String, Object> browserInfo) {
        this.browserInfo = browserInfo;
    }

    public Map<String, Object> getConnectorMetadata() {
        return connectorMetadata;
    }

    public void setConnectorMetadata(Map<String, Object> connectorMetadata) {
        this.connectorMetadata = connectorMetadata;
    }

    public Map<String, Object> getPaymentMethodData() {
        return paymentMethodData;
    }

    public void setPaymentMethodData(Map<String, Object> paymentMethodData) {
        this.paymentMethodData = paymentMethodData;
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

    public Instant getLastSynced() {
        return lastSynced;
    }

    public void setLastSynced(Instant lastSynced) {
        this.lastSynced = lastSynced;
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
        private PaymentAttemptEntity entity = new PaymentAttemptEntity();

        public Builder id(String id) {
            entity.id = id;
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

        public Builder status(String status) {
            entity.status = status;
            return this;
        }

        public Builder connector(String connector) {
            entity.connector = connector;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            entity.errorMessage = errorMessage;
            return this;
        }

        public Builder errorCode(String errorCode) {
            entity.errorCode = errorCode;
            return this;
        }

        public Builder paymentMethodId(String paymentMethodId) {
            entity.paymentMethodId = paymentMethodId;
            return this;
        }

        public Builder authenticationType(String authenticationType) {
            entity.authenticationType = authenticationType;
            return this;
        }

        public Builder connectorTransactionId(String connectorTransactionId) {
            entity.connectorTransactionId = connectorTransactionId;
            return this;
        }

        public Builder amountToCapture(Long amountToCapture) {
            entity.amountToCapture = amountToCapture;
            return this;
        }

        public Builder amountCapturable(Long amountCapturable) {
            entity.amountCapturable = amountCapturable;
            return this;
        }

        public Builder amountCaptured(Long amountCaptured) {
            entity.amountCaptured = amountCaptured;
            return this;
        }

        public Builder browserInfo(Map<String, Object> browserInfo) {
            entity.browserInfo = browserInfo;
            return this;
        }

        public Builder connectorMetadata(Map<String, Object> connectorMetadata) {
            entity.connectorMetadata = connectorMetadata;
            return this;
        }

        public Builder paymentMethodData(Map<String, Object> paymentMethodData) {
            entity.paymentMethodData = paymentMethodData;
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

        public Builder lastSynced(Instant lastSynced) {
            entity.lastSynced = lastSynced;
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

        public PaymentAttemptEntity build() {
            return entity;
        }
    }
}

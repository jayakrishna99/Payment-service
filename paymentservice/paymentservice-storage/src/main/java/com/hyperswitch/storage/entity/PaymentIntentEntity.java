package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Map;

/**
 * Payment Intent entity matching Hyperswitch schema
 */
@Table("payment_intent")
public class PaymentIntentEntity {
    @Id
    @Column("id")
    private String id;
    
    @Column("payment_id")
    private String paymentId;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("status")
    private String status; // IntentStatus enum
    
    @Column("amount")
    private Long amount; // Stored in minor units
    
    @Column("currency")
    private String currency;
    
    @Column("amount_captured")
    private Long amountCaptured;
    
    @Column("customer_id")
    private String customerId;
    
    @Column("description")
    private String description;
    
    @Column("return_url")
    private String returnUrl;
    
    @Column("metadata")
    private Map<String, Object> metadata;
    
    @Column("connector_id")
    private String connectorId;
    
    @Column("active_attempt_id")
    private String activeAttemptId;
    
    @Column("attempt_count")
    private Integer attemptCount;
    
    @Column("profile_id")
    private String profileId;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("modified_at")
    private Instant modifiedAt;
    
    @Column("last_synced")
    private Instant lastSynced;
    
    @Column("setup_future_usage")
    private String setupFutureUsage;
    
    @Column("off_session")
    private Boolean offSession;
    
    @Column("client_secret")
    private String clientSecret;
    
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getAmountCaptured() {
        return amountCaptured;
    }

    public void setAmountCaptured(Long amountCaptured) {
        this.amountCaptured = amountCaptured;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public String getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(String connectorId) {
        this.connectorId = connectorId;
    }

    public String getActiveAttemptId() {
        return activeAttemptId;
    }

    public void setActiveAttemptId(String activeAttemptId) {
        this.activeAttemptId = activeAttemptId;
    }

    public Integer getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(Integer attemptCount) {
        this.attemptCount = attemptCount;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
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

    public String getSetupFutureUsage() {
        return setupFutureUsage;
    }

    public void setSetupFutureUsage(String setupFutureUsage) {
        this.setupFutureUsage = setupFutureUsage;
    }

    public Boolean getOffSession() {
        return offSession;
    }

    public void setOffSession(Boolean offSession) {
        this.offSession = offSession;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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
        private PaymentIntentEntity entity = new PaymentIntentEntity();

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

        public Builder amount(Long amount) {
            entity.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            entity.currency = currency;
            return this;
        }

        public Builder amountCaptured(Long amountCaptured) {
            entity.amountCaptured = amountCaptured;
            return this;
        }

        public Builder customerId(String customerId) {
            entity.customerId = customerId;
            return this;
        }

        public Builder description(String description) {
            entity.description = description;
            return this;
        }

        public Builder returnUrl(String returnUrl) {
            entity.returnUrl = returnUrl;
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            entity.metadata = metadata;
            return this;
        }

        public Builder connectorId(String connectorId) {
            entity.connectorId = connectorId;
            return this;
        }

        public Builder activeAttemptId(String activeAttemptId) {
            entity.activeAttemptId = activeAttemptId;
            return this;
        }

        public Builder attemptCount(Integer attemptCount) {
            entity.attemptCount = attemptCount;
            return this;
        }

        public Builder profileId(String profileId) {
            entity.profileId = profileId;
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

        public Builder setupFutureUsage(String setupFutureUsage) {
            entity.setupFutureUsage = setupFutureUsage;
            return this;
        }

        public Builder offSession(Boolean offSession) {
            entity.offSession = offSession;
            return this;
        }

        public Builder clientSecret(String clientSecret) {
            entity.clientSecret = clientSecret;
            return this;
        }

        public Builder organizationId(String organizationId) {
            entity.organizationId = organizationId;
            return this;
        }

        public PaymentIntentEntity build() {
            return entity;
        }
    }
}

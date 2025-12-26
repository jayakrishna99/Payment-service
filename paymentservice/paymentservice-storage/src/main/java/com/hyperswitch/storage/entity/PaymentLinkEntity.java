package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Map;

/**
 * Payment Link entity matching Hyperswitch schema
 */
@Table("payment_link")
public class PaymentLinkEntity {
    @Id
    @Column("payment_link_id")
    private String paymentLinkId;
    
    @Column("payment_id")
    private String paymentId;
    
    @Column("link_to_pay")
    private String linkToPay;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("amount")
    private Long amount; // Stored in minor units (cents)
    
    @Column("currency")
    private String currency;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("last_modified_at")
    private Instant lastModifiedAt;
    
    @Column("fulfilment_time")
    private Instant fulfilmentTime;
    
    @Column("custom_merchant_name")
    private String customMerchantName;
    
    @Column("payment_link_config")
    private Map<String, Object> paymentLinkConfig;
    
    @Column("description")
    private String description;
    
    @Column("profile_id")
    private String profileId;
    
    @Column("secure_link")
    private String secureLink;
    
    @Column("expires_at")
    private Instant expiresAt;

    // Getters and Setters
    public String getPaymentLinkId() {
        return paymentLinkId;
    }

    public void setPaymentLinkId(String paymentLinkId) {
        this.paymentLinkId = paymentLinkId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getLinkToPay() {
        return linkToPay;
    }

    public void setLinkToPay(String linkToPay) {
        this.linkToPay = linkToPay;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Instant lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public Instant getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setFulfilmentTime(Instant fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public String getCustomMerchantName() {
        return customMerchantName;
    }

    public void setCustomMerchantName(String customMerchantName) {
        this.customMerchantName = customMerchantName;
    }

    public Map<String, Object> getPaymentLinkConfig() {
        return paymentLinkConfig;
    }

    public void setPaymentLinkConfig(Map<String, Object> paymentLinkConfig) {
        this.paymentLinkConfig = paymentLinkConfig;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getSecureLink() {
        return secureLink;
    }

    public void setSecureLink(String secureLink) {
        this.secureLink = secureLink;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PaymentLinkEntity entity = new PaymentLinkEntity();

        public Builder paymentLinkId(String paymentLinkId) {
            entity.paymentLinkId = paymentLinkId;
            return this;
        }

        public Builder paymentId(String paymentId) {
            entity.paymentId = paymentId;
            return this;
        }

        public Builder linkToPay(String linkToPay) {
            entity.linkToPay = linkToPay;
            return this;
        }

        public Builder merchantId(String merchantId) {
            entity.merchantId = merchantId;
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

        public Builder createdAt(Instant createdAt) {
            entity.createdAt = createdAt;
            return this;
        }

        public Builder lastModifiedAt(Instant lastModifiedAt) {
            entity.lastModifiedAt = lastModifiedAt;
            return this;
        }

        public Builder fulfilmentTime(Instant fulfilmentTime) {
            entity.fulfilmentTime = fulfilmentTime;
            return this;
        }

        public Builder customMerchantName(String customMerchantName) {
            entity.customMerchantName = customMerchantName;
            return this;
        }

        public Builder paymentLinkConfig(Map<String, Object> paymentLinkConfig) {
            entity.paymentLinkConfig = paymentLinkConfig;
            return this;
        }

        public Builder description(String description) {
            entity.description = description;
            return this;
        }

        public Builder profileId(String profileId) {
            entity.profileId = profileId;
            return this;
        }

        public Builder secureLink(String secureLink) {
            entity.secureLink = secureLink;
            return this;
        }

        public Builder expiresAt(Instant expiresAt) {
            entity.expiresAt = expiresAt;
            return this;
        }

        public PaymentLinkEntity build() {
            return entity;
        }
    }
}


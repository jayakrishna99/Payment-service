package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.Amount;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Map;

/**
 * Request to create a payment link
 */
public final class PaymentLinkRequest {
    @NotNull
    private final Amount amount;
    
    @NotNull
    private final String merchantId;
    
    private final String description;
    private final String customMerchantName;
    private final Instant expiresAt;
    private final Map<String, Object> paymentLinkConfig;
    private final String profileId;
    private final Boolean generateSecureLink;

    private PaymentLinkRequest(Builder builder) {
        this.amount = builder.amount;
        this.merchantId = builder.merchantId;
        this.description = builder.description;
        this.customMerchantName = builder.customMerchantName;
        this.expiresAt = builder.expiresAt;
        this.paymentLinkConfig = builder.paymentLinkConfig;
        this.profileId = builder.profileId;
        this.generateSecureLink = builder.generateSecureLink;
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

    public String getDescription() {
        return description;
    }

    public String getCustomMerchantName() {
        return customMerchantName;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public Map<String, Object> getPaymentLinkConfig() {
        return paymentLinkConfig;
    }

    public String getProfileId() {
        return profileId;
    }

    public Boolean getGenerateSecureLink() {
        return generateSecureLink;
    }

    public static class Builder {
        private Amount amount;
        private String merchantId;
        private String description;
        private String customMerchantName;
        private Instant expiresAt;
        private Map<String, Object> paymentLinkConfig;
        private String profileId;
        private Boolean generateSecureLink;

        public Builder amount(Amount amount) {
            this.amount = amount;
            return this;
        }

        public Builder merchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder customMerchantName(String customMerchantName) {
            this.customMerchantName = customMerchantName;
            return this;
        }

        public Builder expiresAt(Instant expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public Builder paymentLinkConfig(Map<String, Object> paymentLinkConfig) {
            this.paymentLinkConfig = paymentLinkConfig;
            return this;
        }

        public Builder profileId(String profileId) {
            this.profileId = profileId;
            return this;
        }

        public Builder generateSecureLink(Boolean generateSecureLink) {
            this.generateSecureLink = generateSecureLink;
            return this;
        }

        public PaymentLinkRequest build() {
            return new PaymentLinkRequest(this);
        }
    }
}


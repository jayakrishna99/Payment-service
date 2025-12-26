package com.hyperswitch.common.dto;

import java.time.Instant;
import java.util.Map;

/**
 * Payment Link response DTO
 */
public final class PaymentLinkResponse {
    private final String paymentLinkId;
    private final String paymentId;
    private final String linkToPay;
    private final String secureLink;
    private final String merchantId;
    private final Long amount;
    private final String currency;
    private final String description;
    private final String customMerchantName;
    private final Instant expiresAt;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
    private final Instant fulfilmentTime;
    private final Map<String, Object> paymentLinkConfig;

    private PaymentLinkResponse(Builder builder) {
        this.paymentLinkId = builder.paymentLinkId;
        this.paymentId = builder.paymentId;
        this.linkToPay = builder.linkToPay;
        this.secureLink = builder.secureLink;
        this.merchantId = builder.merchantId;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.description = builder.description;
        this.customMerchantName = builder.customMerchantName;
        this.expiresAt = builder.expiresAt;
        this.createdAt = builder.createdAt;
        this.lastModifiedAt = builder.lastModifiedAt;
        this.fulfilmentTime = builder.fulfilmentTime;
        this.paymentLinkConfig = builder.paymentLinkConfig;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getPaymentLinkId() {
        return paymentLinkId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getLinkToPay() {
        return linkToPay;
    }

    public String getSecureLink() {
        return secureLink;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public Long getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    public Instant getFulfilmentTime() {
        return fulfilmentTime;
    }

    public Map<String, Object> getPaymentLinkConfig() {
        return paymentLinkConfig;
    }

    public static class Builder {
        private String paymentLinkId;
        private String paymentId;
        private String linkToPay;
        private String secureLink;
        private String merchantId;
        private Long amount;
        private String currency;
        private String description;
        private String customMerchantName;
        private Instant expiresAt;
        private Instant createdAt;
        private Instant lastModifiedAt;
        private Instant fulfilmentTime;
        private Map<String, Object> paymentLinkConfig;

        public Builder paymentLinkId(String paymentLinkId) {
            this.paymentLinkId = paymentLinkId;
            return this;
        }

        public Builder paymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder linkToPay(String linkToPay) {
            this.linkToPay = linkToPay;
            return this;
        }

        public Builder secureLink(String secureLink) {
            this.secureLink = secureLink;
            return this;
        }

        public Builder merchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder amount(Long amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
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

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder lastModifiedAt(Instant lastModifiedAt) {
            this.lastModifiedAt = lastModifiedAt;
            return this;
        }

        public Builder fulfilmentTime(Instant fulfilmentTime) {
            this.fulfilmentTime = fulfilmentTime;
            return this;
        }

        public Builder paymentLinkConfig(Map<String, Object> paymentLinkConfig) {
            this.paymentLinkConfig = paymentLinkConfig;
            return this;
        }

        public PaymentLinkResponse build() {
            return new PaymentLinkResponse(this);
        }
    }
}


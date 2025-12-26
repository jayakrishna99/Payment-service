package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.SubscriptionStatus;

import java.time.Instant;

/**
 * Response DTO for subscription information
 */
public class SubscriptionResponse {
    private String subscriptionId;
    private SubscriptionStatus status;
    private String billingProcessor;
    private String paymentMethodId;
    private String merchantConnectorId;
    private String clientSecret;
    private String connectorSubscriptionId;
    private String merchantId;
    private String customerId;
    private String metadata;
    private Instant createdAt;
    private Instant modifiedAt;
    private String profileId;
    private String merchantReferenceId;
    private String planId;
    private String itemPriceId;

    // Getters and Setters
    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public String getBillingProcessor() {
        return billingProcessor;
    }

    public void setBillingProcessor(String billingProcessor) {
        this.billingProcessor = billingProcessor;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getMerchantConnectorId() {
        return merchantConnectorId;
    }

    public void setMerchantConnectorId(String merchantConnectorId) {
        this.merchantConnectorId = merchantConnectorId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getConnectorSubscriptionId() {
        return connectorSubscriptionId;
    }

    public void setConnectorSubscriptionId(String connectorSubscriptionId) {
        this.connectorSubscriptionId = connectorSubscriptionId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
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

    public String getMerchantReferenceId() {
        return merchantReferenceId;
    }

    public void setMerchantReferenceId(String merchantReferenceId) {
        this.merchantReferenceId = merchantReferenceId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getItemPriceId() {
        return itemPriceId;
    }

    public void setItemPriceId(String itemPriceId) {
        this.itemPriceId = itemPriceId;
    }
}


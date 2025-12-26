package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * Entity representing a subscription
 */
@Table("subscription")
public class SubscriptionEntity {

    @Id
    @Column("subscription_id")
    private String subscriptionId;

    @Column("status")
    private String status;

    @Column("billing_processor")
    private String billingProcessor;

    @Column("payment_method_id")
    private String paymentMethodId;

    @Column("merchant_connector_id")
    private String merchantConnectorId;

    @Column("client_secret")
    private String clientSecret;

    @Column("connector_subscription_id")
    private String connectorSubscriptionId;

    @Column("merchant_id")
    private String merchantId;

    @Column("customer_id")
    private String customerId;

    @Column("metadata")
    private String metadata; // JSON string

    @Column("created_at")
    private Instant createdAt;

    @Column("modified_at")
    private Instant modifiedAt;

    @Column("profile_id")
    private String profileId;

    @Column("merchant_reference_id")
    private String merchantReferenceId;

    @Column("plan_id")
    private String planId;

    @Column("item_price_id")
    private String itemPriceId;

    // Getters and Setters
    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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


package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.PayoutStatus;
import com.hyperswitch.common.types.PayoutType;

import java.time.Instant;

/**
 * Response DTO for payout information
 */
public class PayoutResponse {
    private String payoutId;
    private String merchantId;
    private String customerId;
    private String addressId;
    private PayoutType payoutType;
    private String payoutMethodId;
    private Long amount;
    private String destinationCurrency;
    private String sourceCurrency;
    private String description;
    private Boolean recurring;
    private Boolean autoFulfill;
    private String returnUrl;
    private String entityType;
    private String metadata;
    private PayoutStatus status;
    private Instant createdAt;
    private Instant lastModifiedAt;
    private Integer attemptCount;
    private String profileId;
    private String clientSecret;
    private String organizationId;
    private String payoutLinkId; // Payout link ID if payout link was created
    private String payoutLinkUrl; // Payout link URL

    // Getters and Setters
    public String getPayoutId() {
        return payoutId;
    }

    public void setPayoutId(String payoutId) {
        this.payoutId = payoutId;
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

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public PayoutType getPayoutType() {
        return payoutType;
    }

    public void setPayoutType(PayoutType payoutType) {
        this.payoutType = payoutType;
    }

    public String getPayoutMethodId() {
        return payoutMethodId;
    }

    public void setPayoutMethodId(String payoutMethodId) {
        this.payoutMethodId = payoutMethodId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDestinationCurrency() {
        return destinationCurrency;
    }

    public void setDestinationCurrency(String destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public Boolean getAutoFulfill() {
        return autoFulfill;
    }

    public void setAutoFulfill(Boolean autoFulfill) {
        this.autoFulfill = autoFulfill;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public PayoutStatus getStatus() {
        return status;
    }

    public void setStatus(PayoutStatus status) {
        this.status = status;
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

    public String getPayoutLinkId() {
        return payoutLinkId;
    }

    public void setPayoutLinkId(String payoutLinkId) {
        this.payoutLinkId = payoutLinkId;
    }

    public String getPayoutLinkUrl() {
        return payoutLinkUrl;
    }

    public void setPayoutLinkUrl(String payoutLinkUrl) {
        this.payoutLinkUrl = payoutLinkUrl;
    }
}


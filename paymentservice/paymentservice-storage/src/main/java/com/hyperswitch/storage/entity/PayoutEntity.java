package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * Entity representing a payout
 */
@Table("payouts")
public class PayoutEntity {

    @Id
    @Column("payout_id")
    private String payoutId;

    @Column("merchant_id")
    private String merchantId;

    @Column("customer_id")
    private String customerId;

    @Column("address_id")
    private String addressId;

    @Column("payout_type")
    private String payoutType;

    @Column("payout_method_id")
    private String payoutMethodId;

    @Column("amount")
    private Long amount;

    @Column("destination_currency")
    private String destinationCurrency;

    @Column("source_currency")
    private String sourceCurrency;

    @Column("description")
    private String description;

    @Column("recurring")
    private Boolean recurring;

    @Column("auto_fulfill")
    private Boolean autoFulfill;

    @Column("return_url")
    private String returnUrl;

    @Column("entity_type")
    private String entityType;

    @Column("metadata")
    private String metadata; // JSON string

    @Column("created_at")
    private Instant createdAt;

    @Column("last_modified_at")
    private Instant lastModifiedAt;

    @Column("attempt_count")
    private Integer attemptCount;

    @Column("profile_id")
    private String profileId;

    @Column("status")
    private String status;

    @Column("confirm")
    private Boolean confirm;

    @Column("payout_link_id")
    private String payoutLinkId;

    @Column("client_secret")
    private String clientSecret;

    @Column("priority")
    private String priority;

    @Column("organization_id")
    private String organizationId;

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

    public String getPayoutType() {
        return payoutType;
    }

    public void setPayoutType(String payoutType) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public String getPayoutLinkId() {
        return payoutLinkId;
    }

    public void setPayoutLinkId(String payoutLinkId) {
        this.payoutLinkId = payoutLinkId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}


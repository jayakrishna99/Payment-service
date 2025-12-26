package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.Amount;
import com.hyperswitch.common.types.PayoutType;

import java.util.Map;

/**
 * Request to create a payout
 */
public class PayoutRequest {
    private Amount amount;
    private String currency;
    private PayoutType payoutType;
    private String payoutMethodId;
    private String customerId;
    private String addressId;
    private String description;
    private Boolean recurring;
    private Boolean autoFulfill;
    private String returnUrl;
    private String entityType;
    private Map<String, Object> metadata;
    private Boolean confirm;
    private String profileId;
    private Boolean payoutLink; // Whether to create a payout link

    // Getters and Setters
    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public Boolean getPayoutLink() {
        return payoutLink;
    }

    public void setPayoutLink(Boolean payoutLink) {
        this.payoutLink = payoutLink;
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

/**
 * Response DTO for payout link
 */
public class PayoutLinkResponse {
    
    @JsonProperty("payout_id")
    private String payoutId;
    
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @JsonProperty("link_url")
    private String linkUrl;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("expires_at")
    private Instant expiresAt;
    
    @JsonProperty("created_at")
    private Instant createdAt;
    
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
    
    public String getLinkUrl() {
        return linkUrl;
    }
    
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Instant getExpiresAt() {
        return expiresAt;
    }
    
    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
    
    public Instant getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}


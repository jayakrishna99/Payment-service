package com.hyperswitch.common.dto;

import java.time.Instant;

/**
 * Response for payment method collect link
 */
public class PaymentMethodCollectLinkResponse {
    private String collectLinkId;
    private String collectLinkUrl;
    private String customerId;
    private String merchantId;
    private Instant expiresAt;
    private String status;
    
    public PaymentMethodCollectLinkResponse() {
    }
    
    public String getCollectLinkId() {
        return collectLinkId;
    }
    
    public void setCollectLinkId(String collectLinkId) {
        this.collectLinkId = collectLinkId;
    }
    
    public String getCollectLinkUrl() {
        return collectLinkUrl;
    }
    
    public void setCollectLinkUrl(String collectLinkUrl) {
        this.collectLinkUrl = collectLinkUrl;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public Instant getExpiresAt() {
        return expiresAt;
    }
    
    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}


package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Request for initiating a payment method collect link
 */
public class PaymentMethodCollectLinkRequest {
    private String customerId;
    private String merchantId;
    private String returnUrl;
    private Map<String, Object> metadata;
    private Integer expiryTime; // in seconds
    
    public PaymentMethodCollectLinkRequest() {
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
    
    public String getReturnUrl() {
        return returnUrl;
    }
    
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    public Integer getExpiryTime() {
        return expiryTime;
    }
    
    public void setExpiryTime(Integer expiryTime) {
        this.expiryTime = expiryTime;
    }
}


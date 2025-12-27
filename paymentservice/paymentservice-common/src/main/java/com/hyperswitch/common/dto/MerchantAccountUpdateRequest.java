package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Request DTO for updating merchant account
 */
public class MerchantAccountUpdateRequest {
    
    @JsonProperty("merchant_name")
    private String merchantName;
    
    @JsonProperty("merchant_details")
    private Map<String, Object> merchantDetails;
    
    @JsonProperty("metadata")
    private Map<String, Object> metadata;
    
    @JsonProperty("return_url")
    private String returnUrl;
    
    @JsonProperty("webhook_details")
    private Map<String, Object> webhookDetails;
    
    // Getters and Setters
    public String getMerchantName() {
        return merchantName;
    }
    
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    
    public Map<String, Object> getMerchantDetails() {
        return merchantDetails;
    }
    
    public void setMerchantDetails(Map<String, Object> merchantDetails) {
        this.merchantDetails = merchantDetails;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    public String getReturnUrl() {
        return returnUrl;
    }
    
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
    
    public Map<String, Object> getWebhookDetails() {
        return webhookDetails;
    }
    
    public void setWebhookDetails(Map<String, Object> webhookDetails) {
        this.webhookDetails = webhookDetails;
    }
}


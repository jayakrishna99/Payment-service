package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Request DTO for creating merchant account (v1 API)
 */
public class MerchantAccountCreateV1Request {
    
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @JsonProperty("merchant_name")
    private String merchantName;
    
    @JsonProperty("merchant_details")
    private Map<String, Object> merchantDetails;
    
    @JsonProperty("return_url")
    private String returnUrl;
    
    @JsonProperty("webhook_details")
    private Map<String, Object> webhookDetails;
    
    @JsonProperty("metadata")
    private Map<String, Object> metadata;
    
    @JsonProperty("organization_id")
    private String organizationId;
    
    // Getters and Setters
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
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
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    public String getOrganizationId() {
        return organizationId;
    }
    
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Request DTO for creating merchant account (v2 API)
 */
public class MerchantAccountCreateRequest {
    
    @JsonProperty("merchant_name")
    private String merchantName;
    
    @JsonProperty("merchant_details")
    private Map<String, Object> merchantDetails;
    
    @JsonProperty("metadata")
    private Map<String, Object> metadata;
    
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
}


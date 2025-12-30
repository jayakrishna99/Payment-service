package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for sample data operations
 */
public class SampleDataRequest {
    
    @JsonProperty("merchant_id")
    private String merchantId;
    
    // Getters and Setters
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}


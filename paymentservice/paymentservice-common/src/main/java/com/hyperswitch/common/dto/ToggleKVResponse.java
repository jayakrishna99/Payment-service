package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for KV toggle operations
 */
public class ToggleKVResponse {
    
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @JsonProperty("kv_enabled")
    private Boolean kvEnabled;
    
    // Getters and Setters
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public Boolean getKvEnabled() {
        return kvEnabled;
    }
    
    public void setKvEnabled(Boolean kvEnabled) {
        this.kvEnabled = kvEnabled;
    }
}


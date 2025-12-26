package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Response DTO for revenue recovery redis data
 */
@Schema(description = "Response for revenue recovery redis data")
public class RevenueRecoveryRedisResponse {
    
    @Schema(description = "Merchant ID", example = "merchant_123")
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @Schema(description = "Key type", example = "payment_processor_token")
    @JsonProperty("key_type")
    private String keyType;
    
    @Schema(description = "Redis data")
    @JsonProperty("data")
    private Map<String, Object> data;
    
    @Schema(description = "TTL in seconds", example = "3600")
    @JsonProperty("ttl")
    private Long ttl;

    // Getters and Setters
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }
}


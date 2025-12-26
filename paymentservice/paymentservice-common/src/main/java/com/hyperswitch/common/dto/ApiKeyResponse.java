package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

/**
 * Response DTO for API key operations
 */
@Schema(description = "Response for API key operations")
public class ApiKeyResponse {
    
    @Schema(description = "API key ID", example = "12345_key_01926c58bc6e77c09e809964e72af8c8")
    @JsonProperty("key_id")
    private String keyId;
    
    @Schema(description = "Merchant ID", example = "12345_merchant_01926c58bc6e77c09e809964e72af8c8")
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @Schema(description = "API key name", example = "Production API Key")
    @JsonProperty("name")
    private String name;
    
    @Schema(description = "API key description", example = "API key for production environment")
    @JsonProperty("description")
    private String description;
    
    @Schema(description = "API key prefix", example = "snd_")
    @JsonProperty("prefix")
    private String prefix;
    
    @Schema(description = "API key (only shown on creation)", example = "snd_12345_key_01926c58bc6e77c09e809964e72af8c8")
    @JsonProperty("api_key")
    private String apiKey;
    
    @Schema(description = "Created at", example = "2024-02-24T11:04:09.922Z")
    @JsonProperty("created_at")
    private Instant createdAt;
    
    @Schema(description = "Expires at", example = "2025-12-31T23:59:59.999Z")
    @JsonProperty("expires_at")
    private Instant expiresAt;
    
    @Schema(description = "Last used", example = "2024-02-24T11:04:09.922Z")
    @JsonProperty("last_used")
    private Instant lastUsed;

    // Getters and Setters
    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Instant getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Instant lastUsed) {
        this.lastUsed = lastUsed;
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

/**
 * Request DTO for API key operations
 */
@Schema(description = "Request for API key operations")
public class ApiKeyRequest {
    
    @Schema(description = "API key name", example = "Production API Key", required = true)
    @JsonProperty("name")
    private String name;
    
    @Schema(description = "API key description", example = "API key for production environment")
    @JsonProperty("description")
    private String description;
    
    @Schema(description = "Expires at", example = "2025-12-31T23:59:59.999Z")
    @JsonProperty("expires_at")
    private Instant expiresAt;

    // Getters and Setters
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

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}


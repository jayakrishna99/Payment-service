package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.Map;

/**
 * Response DTO for decision manager configuration
 */
@Schema(description = "Decision manager configuration response")
public class DecisionManagerConfigResponse {
    
    @Schema(description = "Decision manager configuration data")
    @JsonProperty("config")
    private Map<String, Object> config;
    
    @Schema(description = "Created timestamp")
    @JsonProperty("created_at")
    private Instant createdAt;
    
    @Schema(description = "Modified timestamp")
    @JsonProperty("modified_at")
    private Instant modifiedAt;

    // Getters and Setters
    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}


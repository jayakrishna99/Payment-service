package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for decision manager configuration
 */
@Schema(description = "Request to upsert decision manager configuration")
public class DecisionManagerConfigRequest {
    
    @Schema(description = "Decision manager configuration data")
    @JsonProperty("config")
    private Map<String, Object> config;

    // Getters and Setters
    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }
}


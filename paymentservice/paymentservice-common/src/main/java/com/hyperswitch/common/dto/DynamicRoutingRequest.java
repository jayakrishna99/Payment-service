package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Request DTO for dynamic routing operations
 */
public class DynamicRoutingRequest {
    
    @JsonProperty("algorithm_id")
    private String algorithmId;
    
    @JsonProperty("config")
    private Map<String, Object> config;
    
    @JsonProperty("enabled")
    private Boolean enabled;
    
    // Getters and Setters
    public String getAlgorithmId() {
        return algorithmId;
    }
    
    public void setAlgorithmId(String algorithmId) {
        this.algorithmId = algorithmId;
    }
    
    public Map<String, Object> getConfig() {
        return config;
    }
    
    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }
    
    public Boolean getEnabled() {
        return enabled;
    }
    
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}


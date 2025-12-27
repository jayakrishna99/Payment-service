package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.Map;

/**
 * Response DTO for dynamic routing operations
 */
public class DynamicRoutingResponse {
    
    @JsonProperty("algorithm_id")
    private String algorithmId;
    
    @JsonProperty("profile_id")
    private String profileId;
    
    @JsonProperty("routing_type")
    private String routingType;
    
    @JsonProperty("config")
    private Map<String, Object> config;
    
    @JsonProperty("enabled")
    private Boolean enabled;
    
    @JsonProperty("created_at")
    private Instant createdAt;
    
    @JsonProperty("updated_at")
    private Instant updatedAt;
    
    // Getters and Setters
    public String getAlgorithmId() {
        return algorithmId;
    }
    
    public void setAlgorithmId(String algorithmId) {
        this.algorithmId = algorithmId;
    }
    
    public String getProfileId() {
        return profileId;
    }
    
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
    
    public String getRoutingType() {
        return routingType;
    }
    
    public void setRoutingType(String routingType) {
        this.routingType = routingType;
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
    
    public Instant getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    
    public Instant getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}


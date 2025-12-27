package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Request DTO for payout routing operations
 */
public class PayoutRoutingRequest {
    
    @JsonProperty("algorithm_id")
    private String algorithmId;
    
    @JsonProperty("profile_id")
    private String profileId;
    
    @JsonProperty("config")
    private Map<String, Object> config;
    
    @JsonProperty("is_default")
    private Boolean isDefault;
    
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
    
    public Map<String, Object> getConfig() {
        return config;
    }
    
    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }
    
    public Boolean getIsDefault() {
        return isDefault;
    }
    
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}


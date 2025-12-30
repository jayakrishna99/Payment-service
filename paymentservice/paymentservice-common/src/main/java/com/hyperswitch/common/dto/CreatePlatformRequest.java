package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for creating platform
 */
public class CreatePlatformRequest {
    
    @JsonProperty("platform_name")
    private String platformName;
    
    // Getters and Setters
    public String getPlatformName() {
        return platformName;
    }
    
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
}


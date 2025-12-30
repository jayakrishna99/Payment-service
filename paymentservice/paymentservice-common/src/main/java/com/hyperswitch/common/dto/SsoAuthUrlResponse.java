package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for SSO auth URL
 */
public class SsoAuthUrlResponse {
    
    @JsonProperty("auth_url")
    private String authUrl;
    
    // Getters and Setters
    public String getAuthUrl() {
        return authUrl;
    }
    
    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }
}


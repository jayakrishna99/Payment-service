package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for getting SSO auth URL
 */
public class GetSsoAuthUrlRequest {
    
    @JsonProperty("auth_id")
    private String authId;
    
    // Getters and Setters
    public String getAuthId() {
        return authId;
    }
    
    public void setAuthId(String authId) {
        this.authId = authId;
    }
}


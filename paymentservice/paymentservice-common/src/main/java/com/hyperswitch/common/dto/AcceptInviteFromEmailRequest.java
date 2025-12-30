package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for accepting invitation from email
 */
public class AcceptInviteFromEmailRequest {
    
    @JsonProperty("token")
    private String token;
    
    // Getters and Setters
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
}


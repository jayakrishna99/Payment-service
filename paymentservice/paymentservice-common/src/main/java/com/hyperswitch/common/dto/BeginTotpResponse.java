package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for beginning TOTP setup
 */
public class BeginTotpResponse {
    
    @JsonProperty("secret")
    private TotpSecret secret;
    
    // Getters and Setters
    public TotpSecret getSecret() {
        return secret;
    }
    
    public void setSecret(TotpSecret secret) {
        this.secret = secret;
    }
}


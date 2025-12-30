package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for verifying TOTP
 */
public class VerifyTotpRequest {
    
    @JsonProperty("totp")
    private String totp;
    
    // Getters and Setters
    public String getTotp() {
        return totp;
    }
    
    public void setTotp(String totp) {
        this.totp = totp;
    }
}


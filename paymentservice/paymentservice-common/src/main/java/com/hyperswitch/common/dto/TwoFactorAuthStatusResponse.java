package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for 2FA status
 */
public class TwoFactorAuthStatusResponse {
    
    @JsonProperty("totp_status")
    private String totpStatus;
    
    @JsonProperty("attempts")
    private Integer attempts;
    
    // Getters and Setters
    public String getTotpStatus() {
        return totpStatus;
    }
    
    public void setTotpStatus(String totpStatus) {
        this.totpStatus = totpStatus;
    }
    
    public Integer getAttempts() {
        return attempts;
    }
    
    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }
}


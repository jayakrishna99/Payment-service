package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for verifying recovery code
 */
public class VerifyRecoveryCodeRequest {
    
    @JsonProperty("recovery_code")
    private String recoveryCode;
    
    // Getters and Setters
    public String getRecoveryCode() {
        return recoveryCode;
    }
    
    public void setRecoveryCode(String recoveryCode) {
        this.recoveryCode = recoveryCode;
    }
}


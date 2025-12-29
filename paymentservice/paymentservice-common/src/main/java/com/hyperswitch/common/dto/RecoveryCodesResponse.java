package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Response DTO for recovery codes
 */
public class RecoveryCodesResponse {
    
    @JsonProperty("recovery_codes")
    private List<String> recoveryCodes;
    
    // Getters and Setters
    public List<String> getRecoveryCodes() {
        return recoveryCodes;
    }
    
    public void setRecoveryCodes(List<String> recoveryCodes) {
        this.recoveryCodes = recoveryCodes;
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for token verification
 */
public class VerifyTokenResponse {
    
    @JsonProperty("valid")
    private Boolean valid;
    
    @JsonProperty("message")
    private String message;
    
    // Getters and Setters
    public Boolean getValid() {
        return valid;
    }
    
    public void setValid(Boolean valid) {
        this.valid = valid;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for authorization operations (signup, signin, etc.)
 */
public class AuthorizeResponse {
    
    @JsonProperty("is_email_sent")
    private Boolean isEmailSent;
    
    // Getters and Setters
    public Boolean getIsEmailSent() {
        return isEmailSent;
    }
    
    public void setIsEmailSent(Boolean isEmailSent) {
        this.isEmailSent = isEmailSent;
    }
}


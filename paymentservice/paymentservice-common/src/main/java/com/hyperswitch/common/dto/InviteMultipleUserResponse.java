package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for inviting multiple users
 */
public class InviteMultipleUserResponse {
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("is_email_sent")
    private Boolean isEmailSent;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("error")
    private String error;
    
    // Getters and Setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Boolean getIsEmailSent() {
        return isEmailSent;
    }
    
    public void setIsEmailSent(Boolean isEmailSent) {
        this.isEmailSent = isEmailSent;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getError() {
        return error;
    }
    
    public void setError(String error) {
        this.error = error;
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for changing password
 */
public class ChangePasswordRequest {
    
    @JsonProperty("new_password")
    private String newPassword;
    
    @JsonProperty("old_password")
    private String oldPassword;
    
    // Getters and Setters
    public String getNewPassword() {
        return newPassword;
    }
    
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    public String getOldPassword() {
        return oldPassword;
    }
    
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}


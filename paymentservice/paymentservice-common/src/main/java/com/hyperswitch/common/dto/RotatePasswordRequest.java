package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for rotating password
 */
public class RotatePasswordRequest {
    
    @JsonProperty("password")
    private String password;
    
    // Getters and Setters
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}


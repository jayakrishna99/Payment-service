package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for SSO sign in
 */
public class SsoSignInRequest {
    
    @JsonProperty("state")
    private String state;
    
    @JsonProperty("code")
    private String code;
    
    // Getters and Setters
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
}


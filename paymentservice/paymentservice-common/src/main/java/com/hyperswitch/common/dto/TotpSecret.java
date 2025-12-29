package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TOTP secret information
 */
public class TotpSecret {
    
    @JsonProperty("secret")
    private String secret;
    
    @JsonProperty("totp_url")
    private String totpUrl;
    
    // Getters and Setters
    public String getSecret() {
        return secret;
    }
    
    public void setSecret(String secret) {
        this.secret = secret;
    }
    
    public String getTotpUrl() {
        return totpUrl;
    }
    
    public void setTotpUrl(String totpUrl) {
        this.totpUrl = totpUrl;
    }
}


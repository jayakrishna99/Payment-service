package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Request DTO for updating a theme
 */
public class UpdateThemeRequest {
    
    @JsonProperty("theme_data")
    private Map<String, Object> themeData;
    
    @JsonProperty("email_config")
    private Map<String, Object> emailConfig;
    
    // Getters and Setters
    public Map<String, Object> getThemeData() {
        return themeData;
    }
    
    public void setThemeData(Map<String, Object> themeData) {
        this.themeData = themeData;
    }
    
    public Map<String, Object> getEmailConfig() {
        return emailConfig;
    }
    
    public void setEmailConfig(Map<String, Object> emailConfig) {
        this.emailConfig = emailConfig;
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Request DTO for creating a theme
 */
public class CreateThemeRequest {
    
    @JsonProperty("theme_name")
    private String themeName;
    
    @JsonProperty("theme_data")
    private Map<String, Object> themeData;
    
    @JsonProperty("email_config")
    private Map<String, Object> emailConfig;
    
    @JsonProperty("entity_type")
    private String entityType;
    
    // Getters and Setters
    public String getThemeName() {
        return themeName;
    }
    
    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
    
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
    
    public String getEntityType() {
        return entityType;
    }
    
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}


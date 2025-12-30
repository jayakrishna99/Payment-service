package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.Map;

/**
 * Response DTO for theme operations
 */
public class ThemeResponse {
    
    @JsonProperty("theme_id")
    private String themeId;
    
    @JsonProperty("theme_name")
    private String themeName;
    
    @JsonProperty("entity_type")
    private String entityType;
    
    @JsonProperty("org_id")
    private String orgId;
    
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @JsonProperty("profile_id")
    private String profileId;
    
    @JsonProperty("theme_data")
    private Map<String, Object> themeData;
    
    @JsonProperty("email_config")
    private Map<String, Object> emailConfig;
    
    @JsonProperty("created_at")
    private Instant createdAt;
    
    @JsonProperty("updated_at")
    private Instant updatedAt;
    
    // Getters and Setters
    public String getThemeId() {
        return themeId;
    }
    
    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }
    
    public String getThemeName() {
        return themeName;
    }
    
    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
    
    public String getEntityType() {
        return entityType;
    }
    
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    
    public String getOrgId() {
        return orgId;
    }
    
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getProfileId() {
        return profileId;
    }
    
    public void setProfileId(String profileId) {
        this.profileId = profileId;
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
    
    public Instant getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    
    public Instant getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}


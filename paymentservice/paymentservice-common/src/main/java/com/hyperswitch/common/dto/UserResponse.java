package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

/**
 * Response DTO for user details
 */
public class UserResponse {
    
    @JsonProperty("user_id")
    private String userId;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("is_verified")
    private Boolean isVerified;
    
    @JsonProperty("created_at")
    private Instant createdAt;
    
    @JsonProperty("last_modified_at")
    private Instant lastModifiedAt;
    
    @JsonProperty("totp_status")
    private String totpStatus;
    
    // Getters and Setters
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Boolean getIsVerified() {
        return isVerified;
    }
    
    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }
    
    public Instant getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    
    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }
    
    public void setLastModifiedAt(Instant lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
    
    public String getTotpStatus() {
        return totpStatus;
    }
    
    public void setTotpStatus(String totpStatus) {
        this.totpStatus = totpStatus;
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for creating user authentication method
 */
public class CreateUserAuthenticationMethodResponse {
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("auth_id")
    private String authId;
    
    @JsonProperty("owner_id")
    private String ownerId;
    
    @JsonProperty("owner_type")
    private String ownerType;
    
    @JsonProperty("auth_type")
    private String authType;
    
    @JsonProperty("email_domain")
    private String emailDomain;
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getAuthId() {
        return authId;
    }
    
    public void setAuthId(String authId) {
        this.authId = authId;
    }
    
    public String getOwnerId() {
        return ownerId;
    }
    
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    
    public String getOwnerType() {
        return ownerType;
    }
    
    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }
    
    public String getAuthType() {
        return authType;
    }
    
    public void setAuthType(String authType) {
        this.authType = authType;
    }
    
    public String getEmailDomain() {
        return emailDomain;
    }
    
    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }
}


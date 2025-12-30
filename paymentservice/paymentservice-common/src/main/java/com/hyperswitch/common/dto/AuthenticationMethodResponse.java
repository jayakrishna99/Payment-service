package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Response DTO for authentication method
 */
public class AuthenticationMethodResponse {
    
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
    
    @JsonProperty("auth_method")
    private Map<String, Object> authMethod;
    
    @JsonProperty("allow_signup")
    private Boolean allowSignup;
    
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
    
    public Map<String, Object> getAuthMethod() {
        return authMethod;
    }
    
    public void setAuthMethod(Map<String, Object> authMethod) {
        this.authMethod = authMethod;
    }
    
    public Boolean getAllowSignup() {
        return allowSignup;
    }
    
    public void setAllowSignup(Boolean allowSignup) {
        this.allowSignup = allowSignup;
    }
    
    public String getEmailDomain() {
        return emailDomain;
    }
    
    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }
}


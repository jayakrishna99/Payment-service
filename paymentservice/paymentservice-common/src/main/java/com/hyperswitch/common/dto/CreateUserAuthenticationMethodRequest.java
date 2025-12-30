package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Request DTO for creating user authentication method
 */
public class CreateUserAuthenticationMethodRequest {
    
    @JsonProperty("owner_id")
    private String ownerId;
    
    @JsonProperty("owner_type")
    private String ownerType;
    
    @JsonProperty("auth_method")
    private Map<String, Object> authMethod;
    
    @JsonProperty("allow_signup")
    private Boolean allowSignup;
    
    @JsonProperty("email_domain")
    private String emailDomain;
    
    // Getters and Setters
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


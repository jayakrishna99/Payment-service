package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Request DTO for updating user authentication method
 */
public class UpdateUserAuthenticationMethodRequest {
    
    @JsonProperty("id")
    private String id;
    
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


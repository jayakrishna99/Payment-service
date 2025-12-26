package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Response DTO for authentication session token
 */
@Schema(description = "Response for authentication session token")
public class AuthenticationSessionTokenResponse {
    
    @Schema(description = "Session token", example = "token_123456")
    @JsonProperty("session_token")
    private String sessionToken;
    
    @Schema(description = "Token expiry time in seconds", example = "3600")
    @JsonProperty("expires_in")
    private Integer expiresIn;
    
    @Schema(description = "Additional metadata")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    // Getters and Setters
    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}


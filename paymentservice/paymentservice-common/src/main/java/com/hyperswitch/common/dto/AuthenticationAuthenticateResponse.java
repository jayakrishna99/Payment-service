package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Response DTO for authentication authenticate
 */
@Schema(description = "Response for authentication authenticate")
public class AuthenticationAuthenticateResponse {
    
    @Schema(description = "Authentication ID", example = "auth_123456")
    @JsonProperty("authentication_id")
    private String authenticationId;
    
    @Schema(description = "Authentication status", example = "SUCCESSFUL")
    @JsonProperty("authentication_status")
    private String authenticationStatus;
    
    @Schema(description = "Authentication lifecycle status", example = "COMPLETED")
    @JsonProperty("authentication_lifecycle_status")
    private String authenticationLifecycleStatus;
    
    @Schema(description = "Redirect URL if required")
    @JsonProperty("redirect_url")
    private String redirectUrl;
    
    @Schema(description = "Challenge URL if required")
    @JsonProperty("challenge_url")
    private String challengeUrl;
    
    @Schema(description = "Additional metadata")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    // Getters and Setters
    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(String authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public String getAuthenticationLifecycleStatus() {
        return authenticationLifecycleStatus;
    }

    public void setAuthenticationLifecycleStatus(String authenticationLifecycleStatus) {
        this.authenticationLifecycleStatus = authenticationLifecycleStatus;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getChallengeUrl() {
        return challengeUrl;
    }

    public void setChallengeUrl(String challengeUrl) {
        this.challengeUrl = challengeUrl;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}


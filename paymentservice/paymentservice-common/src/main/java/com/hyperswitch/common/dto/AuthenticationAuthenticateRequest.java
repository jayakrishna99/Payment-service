package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for authenticating payment
 */
@Schema(description = "Request for authenticating payment")
public class AuthenticationAuthenticateRequest {
    
    @Schema(description = "Authentication data")
    @JsonProperty("authentication_data")
    private Map<String, Object> authenticationData;
    
    @Schema(description = "Additional parameters")
    @JsonProperty("parameters")
    private Map<String, Object> parameters;

    // Getters and Setters
    public Map<String, Object> getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(Map<String, Object> authenticationData) {
        this.authenticationData = authenticationData;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}


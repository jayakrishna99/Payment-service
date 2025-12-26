package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for creating/updating routing configuration
 */
@Schema(description = "Request to create or update routing configuration")
public class RoutingConfigRequest {
    
    @Schema(description = "Name of the routing configuration", example = "priority-routing-1", maxLength = 64)
    @JsonProperty("name")
    private String name;
    
    @Schema(description = "Description of the routing configuration")
    @JsonProperty("description")
    private String description;
    
    @Schema(description = "Routing algorithm configuration")
    @JsonProperty("algorithm")
    private Map<String, Object> algorithm;
    
    @Schema(description = "Profile ID for the routing configuration", example = "profile_123")
    @JsonProperty("profile_id")
    private String profileId;
    
    @Schema(description = "Transaction type", example = "payment")
    @JsonProperty("transaction_type")
    private String transactionType;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Map<String, Object> algorithm) {
        this.algorithm = algorithm;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}


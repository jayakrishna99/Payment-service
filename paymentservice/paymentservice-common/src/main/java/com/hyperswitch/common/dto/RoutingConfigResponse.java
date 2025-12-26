package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.Map;

/**
 * Response DTO for routing configuration
 */
@Schema(description = "Routing configuration response")
public class RoutingConfigResponse {
    
    @Schema(description = "Algorithm ID", example = "routing_123")
    @JsonProperty("algorithm_id")
    private String algorithmId;
    
    @Schema(description = "Name of the routing configuration", example = "priority-routing-1")
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
    
    @Schema(description = "Whether this is the active routing configuration")
    @JsonProperty("is_active")
    private Boolean isActive;
    
    @Schema(description = "Whether this is the default routing configuration")
    @JsonProperty("is_default")
    private Boolean isDefault;
    
    @Schema(description = "Created timestamp")
    @JsonProperty("created_at")
    private Instant createdAt;
    
    @Schema(description = "Modified timestamp")
    @JsonProperty("modified_at")
    private Instant modifiedAt;

    // Getters and Setters
    public String getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(String algorithmId) {
        this.algorithmId = algorithmId;
    }

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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for migrating routing rules
 */
@Schema(description = "Request to migrate routing rules")
public class RoutingRuleMigrationRequest {
    
    @Schema(description = "Profile ID", example = "profile_123")
    @JsonProperty("profile_id")
    private String profileId;
    
    @Schema(description = "Source algorithm ID", example = "routing_old")
    @JsonProperty("source_algorithm_id")
    private String sourceAlgorithmId;
    
    @Schema(description = "Target algorithm ID", example = "routing_new")
    @JsonProperty("target_algorithm_id")
    private String targetAlgorithmId;

    // Getters and Setters
    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getSourceAlgorithmId() {
        return sourceAlgorithmId;
    }

    public void setSourceAlgorithmId(String sourceAlgorithmId) {
        this.sourceAlgorithmId = sourceAlgorithmId;
    }

    public String getTargetAlgorithmId() {
        return targetAlgorithmId;
    }

    public void setTargetAlgorithmId(String targetAlgorithmId) {
        this.targetAlgorithmId = targetAlgorithmId;
    }
}


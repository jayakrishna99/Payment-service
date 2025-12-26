package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response DTO for executing 3DS decision rule
 */
@Schema(description = "Response for executing 3DS decision rule")
public class ThreeDsDecisionRuleExecuteResponse {
    
    @Schema(description = "The decision made by the 3DS decision rule engine", 
            example = "challenge_requested", required = true)
    @JsonProperty("decision")
    private String decision;

    // Getters and Setters
    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}


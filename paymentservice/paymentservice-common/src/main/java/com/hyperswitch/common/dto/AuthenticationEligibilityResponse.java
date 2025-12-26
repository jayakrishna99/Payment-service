package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

/**
 * Response DTO for authentication eligibility check
 */
@Schema(description = "Response for authentication eligibility check")
public class AuthenticationEligibilityResponse {
    
    @Schema(description = "Is eligible", example = "true")
    @JsonProperty("eligible")
    private Boolean eligible;
    
    @Schema(description = "Eligibility check ID", example = "elig_check_123")
    @JsonProperty("eligibility_check_id")
    private String eligibilityCheckId;
    
    @Schema(description = "Available authentication methods")
    @JsonProperty("available_methods")
    private List<String> availableMethods;
    
    @Schema(description = "Reason for eligibility/ineligibility")
    @JsonProperty("reason")
    private String reason;
    
    @Schema(description = "Additional metadata")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    // Getters and Setters
    public Boolean getEligible() {
        return eligible;
    }

    public void setEligible(Boolean eligible) {
        this.eligible = eligible;
    }

    public String getEligibilityCheckId() {
        return eligibilityCheckId;
    }

    public void setEligibilityCheckId(String eligibilityCheckId) {
        this.eligibilityCheckId = eligibilityCheckId;
    }

    public List<String> getAvailableMethods() {
        return availableMethods;
    }

    public void setAvailableMethods(List<String> availableMethods) {
        this.availableMethods = availableMethods;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}


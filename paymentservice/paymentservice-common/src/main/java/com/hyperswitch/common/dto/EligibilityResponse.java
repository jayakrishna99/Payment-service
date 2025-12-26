package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Response DTO for payment eligibility
 */
@Schema(description = "Response for payment eligibility")
public class EligibilityResponse {
    
    @Schema(description = "Is eligible", example = "true")
    @JsonProperty("eligible")
    private Boolean eligible;
    
    @Schema(description = "Available balance in minor units")
    @JsonProperty("available_balance")
    private Long availableBalance;
    
    @Schema(description = "Currency code", example = "USD")
    @JsonProperty("currency")
    private String currency;
    
    @Schema(description = "Eligibility reason")
    @JsonProperty("reason")
    private String reason;
    
    @Schema(description = "Additional eligibility information")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    // Getters and Setters
    public Boolean getEligible() {
        return eligible;
    }

    public void setEligible(Boolean eligible) {
        this.eligible = eligible;
    }

    public Long getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Long availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Response DTO for Apple Pay verified domains
 */
@Schema(description = "Response for Apple Pay verified domains")
public class ApplePayVerifiedDomainsResponse {
    
    @Schema(description = "List of verified domains")
    @JsonProperty("verified_domains")
    private List<String> verifiedDomains;

    // Getters and Setters
    public List<String> getVerifiedDomains() {
        return verifiedDomains;
    }

    public void setVerifiedDomains(List<String> verifiedDomains) {
        this.verifiedDomains = verifiedDomains;
    }
}


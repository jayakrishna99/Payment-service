package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response DTO for payment method auth link token
 */
@Schema(description = "Response for payment method auth link token")
public class PaymentMethodAuthLinkResponse {
    
    @Schema(description = "Link token", example = "link_token_1234567890")
    @JsonProperty("link_token")
    private String linkToken;
    
    @Schema(description = "Connector name", example = "stripe")
    @JsonProperty("connector")
    private String connector;

    // Getters and Setters
    public String getLinkToken() {
        return linkToken;
    }

    public void setLinkToken(String linkToken) {
        this.linkToken = linkToken;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }
}


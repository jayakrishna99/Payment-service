package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response DTO for Apple Pay merchant verification
 */
@Schema(description = "Response for Apple Pay merchant verification")
public class ApplePayMerchantResponse {
    
    @Schema(description = "Status message", example = "Applepay verification Completed")
    @JsonProperty("status_message")
    private String statusMessage;

    // Getters and Setters
    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}


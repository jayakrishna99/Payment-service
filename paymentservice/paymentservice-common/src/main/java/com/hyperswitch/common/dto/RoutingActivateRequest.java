package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for activating a routing algorithm
 */
@Schema(description = "Request to activate a routing algorithm")
public class RoutingActivateRequest {
    
    @Schema(description = "Transaction type", example = "payment")
    @JsonProperty("transaction_type")
    private String transactionType;

    // Getters and Setters
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}


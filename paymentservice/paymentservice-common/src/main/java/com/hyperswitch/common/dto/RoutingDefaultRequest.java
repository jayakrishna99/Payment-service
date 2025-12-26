package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for setting default routing configuration
 */
@Schema(description = "Request to set default routing configuration")
public class RoutingDefaultRequest {
    
    @Schema(description = "Algorithm ID to set as default", example = "routing_123")
    @JsonProperty("algorithm_id")
    private String algorithmId;
    
    @Schema(description = "Transaction type", example = "payment")
    @JsonProperty("transaction_type")
    private String transactionType;

    // Getters and Setters
    public String getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(String algorithmId) {
        this.algorithmId = algorithmId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}


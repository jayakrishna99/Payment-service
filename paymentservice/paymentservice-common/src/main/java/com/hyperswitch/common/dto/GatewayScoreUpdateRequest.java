package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for updating gateway score
 */
@Schema(description = "Request to update gateway score")
public class GatewayScoreUpdateRequest {
    
    @Schema(description = "Connector name", example = "stripe")
    @JsonProperty("connector")
    private String connector;
    
    @Schema(description = "Score value", example = "0.95")
    @JsonProperty("score")
    private Double score;
    
    @Schema(description = "Transaction ID", example = "pay_123")
    @JsonProperty("transaction_id")
    private String transactionId;
    
    @Schema(description = "Success status", example = "true")
    @JsonProperty("success")
    private Boolean success;

    // Getters and Setters
    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}


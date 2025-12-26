package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for evaluating a routing rule
 */
@Schema(description = "Request to evaluate a routing rule")
public class RoutingEvaluationRequest {
    
    @Schema(description = "Payment request data for evaluation")
    @JsonProperty("payment_request")
    private Map<String, Object> paymentRequest;
    
    @Schema(description = "Routing rule to evaluate")
    @JsonProperty("rule")
    private Map<String, Object> rule;

    // Getters and Setters
    public Map<String, Object> getPaymentRequest() {
        return paymentRequest;
    }

    public void setPaymentRequest(Map<String, Object> paymentRequest) {
        this.paymentRequest = paymentRequest;
    }

    public Map<String, Object> getRule() {
        return rule;
    }

    public void setRule(Map<String, Object> rule) {
        this.rule = rule;
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

/**
 * Response DTO for routing rule evaluation
 */
@Schema(description = "Response for routing rule evaluation")
public class RoutingEvaluationResponse {
    
    @Schema(description = "Selected connectors")
    @JsonProperty("connectors")
    private List<String> connectors;
    
    @Schema(description = "Evaluation result details")
    @JsonProperty("result")
    private Map<String, Object> result;

    // Getters and Setters
    public List<String> getConnectors() {
        return connectors;
    }

    public void setConnectors(List<String> connectors) {
        this.connectors = connectors;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}


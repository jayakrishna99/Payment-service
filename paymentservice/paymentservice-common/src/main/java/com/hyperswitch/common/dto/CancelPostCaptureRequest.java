package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for cancel post capture
 */
@Schema(description = "Request for cancel post capture")
public class CancelPostCaptureRequest {
    
    @Schema(description = "Cancellation reason")
    @JsonProperty("reason")
    private String reason;
    
    @Schema(description = "Additional cancellation parameters")
    @JsonProperty("parameters")
    private Map<String, Object> parameters;

    // Getters and Setters
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}


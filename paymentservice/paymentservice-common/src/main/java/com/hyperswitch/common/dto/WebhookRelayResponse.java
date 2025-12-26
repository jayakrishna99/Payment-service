package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

/**
 * Response DTO for webhook relay operations
 */
public class WebhookRelayResponse {
    
    @JsonProperty("relay_id")
    private String relayId;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("status_code")
    private Integer statusCode;
    
    @JsonProperty("response_body")
    private String responseBody;
    
    @JsonProperty("relayed_at")
    private Instant relayedAt;
    
    // Getters and Setters
    public String getRelayId() {
        return relayId;
    }
    
    public void setRelayId(String relayId) {
        this.relayId = relayId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Integer getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    
    public String getResponseBody() {
        return responseBody;
    }
    
    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
    
    public Instant getRelayedAt() {
        return relayedAt;
    }
    
    public void setRelayedAt(Instant relayedAt) {
        this.relayedAt = relayedAt;
    }
}


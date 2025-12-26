package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

/**
 * Response DTO for network token requestor webhook
 */
public class NetworkTokenWebhookResponse {
    
    @JsonProperty("webhook_id")
    private String webhookId;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("processed_at")
    private Instant processedAt;
    
    // Getters and Setters
    public String getWebhookId() {
        return webhookId;
    }
    
    public void setWebhookId(String webhookId) {
        this.webhookId = webhookId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Instant getProcessedAt() {
        return processedAt;
    }
    
    public void setProcessedAt(Instant processedAt) {
        this.processedAt = processedAt;
    }
}


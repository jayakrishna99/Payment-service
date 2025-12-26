package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response DTO for webhook
 */
@Schema(description = "Response for webhook")
public class WebhookResponse {
    
    @Schema(description = "Webhook processed", example = "true")
    @JsonProperty("processed")
    private Boolean processed;
    
    @Schema(description = "Webhook ID", example = "wh_123456")
    @JsonProperty("webhook_id")
    private String webhookId;
    
    @Schema(description = "Message", example = "Webhook processed successfully")
    @JsonProperty("message")
    private String message;

    // Getters and Setters
    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public String getWebhookId() {
        return webhookId;
    }

    public void setWebhookId(String webhookId) {
        this.webhookId = webhookId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


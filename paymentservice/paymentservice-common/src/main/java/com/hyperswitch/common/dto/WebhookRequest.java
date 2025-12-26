package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for webhook
 */
@Schema(description = "Request for webhook")
public class WebhookRequest {
    
    @Schema(description = "Webhook event type", example = "payment_intent.succeeded")
    @JsonProperty("event_type")
    private String eventType;
    
    @Schema(description = "Webhook data")
    @JsonProperty("data")
    private Map<String, Object> data;
    
    @Schema(description = "Webhook signature")
    @JsonProperty("signature")
    private String signature;
    
    @Schema(description = "Additional headers")
    @JsonProperty("headers")
    private Map<String, String> headers;

    // Getters and Setters
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}


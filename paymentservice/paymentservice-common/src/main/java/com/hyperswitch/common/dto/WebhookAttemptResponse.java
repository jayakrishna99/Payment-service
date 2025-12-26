package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

/**
 * Response DTO for webhook delivery attempt
 */
public class WebhookAttemptResponse {
    
    @JsonProperty("attempt_id")
    private String attemptId;
    
    @JsonProperty("event_id")
    private String eventId;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("status_code")
    private Integer statusCode;
    
    @JsonProperty("response_body")
    private String responseBody;
    
    @JsonProperty("attempted_at")
    private Instant attemptedAt;
    
    @JsonProperty("attempt_number")
    private Integer attemptNumber;
    
    // Getters and Setters
    public String getAttemptId() {
        return attemptId;
    }
    
    public void setAttemptId(String attemptId) {
        this.attemptId = attemptId;
    }
    
    public String getEventId() {
        return eventId;
    }
    
    public void setEventId(String eventId) {
        this.eventId = eventId;
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
    
    public Instant getAttemptedAt() {
        return attemptedAt;
    }
    
    public void setAttemptedAt(Instant attemptedAt) {
        this.attemptedAt = attemptedAt;
    }
    
    public Integer getAttemptNumber() {
        return attemptNumber;
    }
    
    public void setAttemptNumber(Integer attemptNumber) {
        this.attemptNumber = attemptNumber;
    }
}


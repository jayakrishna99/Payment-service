package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for outgoing webhook event logs
 */
public class OutgoingWebhookEventLogsResponse {
    
    @JsonProperty("events")
    private List<OutgoingWebhookEventLog> events;
    
    @JsonProperty("total_count")
    private Long totalCount;
    
    // Getters and Setters
    public List<OutgoingWebhookEventLog> getEvents() {
        return events;
    }
    
    public void setEvents(List<OutgoingWebhookEventLog> events) {
        this.events = events;
    }
    
    public Long getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
    
    /**
     * Individual outgoing webhook event log entry
     */
    public static class OutgoingWebhookEventLog {
        @JsonProperty("event_id")
        private String eventId;
        
        @JsonProperty("timestamp")
        private String timestamp;
        
        @JsonProperty("webhook_url")
        private String webhookUrl;
        
        @JsonProperty("event_type")
        private String eventType;
        
        @JsonProperty("status_code")
        private Integer statusCode;
        
        @JsonProperty("response_body")
        private Map<String, Object> responseBody;
        
        @JsonProperty("attempt_count")
        private Integer attemptCount;
        
        @JsonProperty("metadata")
        private Map<String, Object> metadata;
        
        // Getters and Setters
        public String getEventId() {
            return eventId;
        }
        
        public void setEventId(String eventId) {
            this.eventId = eventId;
        }
        
        public String getTimestamp() {
            return timestamp;
        }
        
        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
        
        public String getWebhookUrl() {
            return webhookUrl;
        }
        
        public void setWebhookUrl(String webhookUrl) {
            this.webhookUrl = webhookUrl;
        }
        
        public String getEventType() {
            return eventType;
        }
        
        public void setEventType(String eventType) {
            this.eventType = eventType;
        }
        
        public Integer getStatusCode() {
            return statusCode;
        }
        
        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }
        
        public Map<String, Object> getResponseBody() {
            return responseBody;
        }
        
        public void setResponseBody(Map<String, Object> responseBody) {
            this.responseBody = responseBody;
        }
        
        public Integer getAttemptCount() {
            return attemptCount;
        }
        
        public void setAttemptCount(Integer attemptCount) {
            this.attemptCount = attemptCount;
        }
        
        public Map<String, Object> getMetadata() {
            return metadata;
        }
        
        public void setMetadata(Map<String, Object> metadata) {
            this.metadata = metadata;
        }
    }
}


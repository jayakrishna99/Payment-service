package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for connector event logs
 */
public class ConnectorEventLogsResponse {
    
    @JsonProperty("events")
    private List<ConnectorEventLog> events;
    
    @JsonProperty("total_count")
    private Long totalCount;
    
    // Getters and Setters
    public List<ConnectorEventLog> getEvents() {
        return events;
    }
    
    public void setEvents(List<ConnectorEventLog> events) {
        this.events = events;
    }
    
    public Long getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
    
    /**
     * Individual connector event log entry
     */
    public static class ConnectorEventLog {
        @JsonProperty("event_id")
        private String eventId;
        
        @JsonProperty("timestamp")
        private String timestamp;
        
        @JsonProperty("connector")
        private String connector;
        
        @JsonProperty("event_type")
        private String eventType;
        
        @JsonProperty("request_body")
        private Map<String, Object> requestBody;
        
        @JsonProperty("response_body")
        private Map<String, Object> responseBody;
        
        @JsonProperty("status_code")
        private Integer statusCode;
        
        @JsonProperty("latency_ms")
        private Long latencyMs;
        
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
        
        public String getConnector() {
            return connector;
        }
        
        public void setConnector(String connector) {
            this.connector = connector;
        }
        
        public String getEventType() {
            return eventType;
        }
        
        public void setEventType(String eventType) {
            this.eventType = eventType;
        }
        
        public Map<String, Object> getRequestBody() {
            return requestBody;
        }
        
        public void setRequestBody(Map<String, Object> requestBody) {
            this.requestBody = requestBody;
        }
        
        public Map<String, Object> getResponseBody() {
            return responseBody;
        }
        
        public void setResponseBody(Map<String, Object> responseBody) {
            this.responseBody = responseBody;
        }
        
        public Integer getStatusCode() {
            return statusCode;
        }
        
        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }
        
        public Long getLatencyMs() {
            return latencyMs;
        }
        
        public void setLatencyMs(Long latencyMs) {
            this.latencyMs = latencyMs;
        }
        
        public Map<String, Object> getMetadata() {
            return metadata;
        }
        
        public void setMetadata(Map<String, Object> metadata) {
            this.metadata = metadata;
        }
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for routing event logs
 */
public class RoutingEventLogsResponse {
    
    @JsonProperty("events")
    private List<RoutingEventLog> events;
    
    @JsonProperty("total_count")
    private Long totalCount;
    
    // Getters and Setters
    public List<RoutingEventLog> getEvents() {
        return events;
    }
    
    public void setEvents(List<RoutingEventLog> events) {
        this.events = events;
    }
    
    public Long getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
    
    /**
     * Individual routing event log entry
     */
    public static class RoutingEventLog {
        @JsonProperty("event_id")
        private String eventId;
        
        @JsonProperty("timestamp")
        private String timestamp;
        
        @JsonProperty("algorithm")
        private String algorithm;
        
        @JsonProperty("connector")
        private String connector;
        
        @JsonProperty("status")
        private String status;
        
        @JsonProperty("routing_decision")
        private String routingDecision;
        
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
        
        public String getAlgorithm() {
            return algorithm;
        }
        
        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }
        
        public String getConnector() {
            return connector;
        }
        
        public void setConnector(String connector) {
            this.connector = connector;
        }
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
        
        public String getRoutingDecision() {
            return routingDecision;
        }
        
        public void setRoutingDecision(String routingDecision) {
            this.routingDecision = routingDecision;
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


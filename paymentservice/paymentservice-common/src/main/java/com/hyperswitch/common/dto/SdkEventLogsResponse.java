package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for SDK event logs
 */
public class SdkEventLogsResponse {
    
    @JsonProperty("events")
    private List<SdkEventLog> events;
    
    @JsonProperty("total_count")
    private Long totalCount;
    
    // Getters and Setters
    public List<SdkEventLog> getEvents() {
        return events;
    }
    
    public void setEvents(List<SdkEventLog> events) {
        this.events = events;
    }
    
    public Long getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
    
    /**
     * Individual SDK event log entry
     */
    public static class SdkEventLog {
        @JsonProperty("event_id")
        private String eventId;
        
        @JsonProperty("timestamp")
        private String timestamp;
        
        @JsonProperty("event_type")
        private String eventType;
        
        @JsonProperty("payment_method")
        private String paymentMethod;
        
        @JsonProperty("platform")
        private String platform;
        
        @JsonProperty("browser_name")
        private String browserName;
        
        @JsonProperty("component")
        private String component;
        
        @JsonProperty("payment_experience")
        private String paymentExperience;
        
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
        
        public String getEventType() {
            return eventType;
        }
        
        public void setEventType(String eventType) {
            this.eventType = eventType;
        }
        
        public String getPaymentMethod() {
            return paymentMethod;
        }
        
        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }
        
        public String getPlatform() {
            return platform;
        }
        
        public void setPlatform(String platform) {
            this.platform = platform;
        }
        
        public String getBrowserName() {
            return browserName;
        }
        
        public void setBrowserName(String browserName) {
            this.browserName = browserName;
        }
        
        public String getComponent() {
            return component;
        }
        
        public void setComponent(String component) {
            this.component = component;
        }
        
        public String getPaymentExperience() {
            return paymentExperience;
        }
        
        public void setPaymentExperience(String paymentExperience) {
            this.paymentExperience = paymentExperience;
        }
        
        public Map<String, Object> getMetadata() {
            return metadata;
        }
        
        public void setMetadata(Map<String, Object> metadata) {
            this.metadata = metadata;
        }
    }
}


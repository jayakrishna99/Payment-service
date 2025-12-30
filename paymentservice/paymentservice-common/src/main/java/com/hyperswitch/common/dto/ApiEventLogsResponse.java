package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for API event logs
 */
public class ApiEventLogsResponse {
    
    @JsonProperty("events")
    private List<ApiEventLog> events;
    
    @JsonProperty("total_count")
    private Long totalCount;
    
    // Getters and Setters
    public List<ApiEventLog> getEvents() {
        return events;
    }
    
    public void setEvents(List<ApiEventLog> events) {
        this.events = events;
    }
    
    public Long getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
    
    /**
     * Individual API event log entry
     */
    public static class ApiEventLog {
        @JsonProperty("event_id")
        private String eventId;
        
        @JsonProperty("timestamp")
        private String timestamp;
        
        @JsonProperty("api_flow")
        private String apiFlow;
        
        @JsonProperty("flow_type")
        private String flowType;
        
        @JsonProperty("status_code")
        private Integer statusCode;
        
        @JsonProperty("latency_ms")
        private Long latencyMs;
        
        @JsonProperty("request_method")
        private String requestMethod;
        
        @JsonProperty("request_path")
        private String requestPath;
        
        @JsonProperty("response_body")
        private Map<String, Object> responseBody;
        
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
        
        public String getApiFlow() {
            return apiFlow;
        }
        
        public void setApiFlow(String apiFlow) {
            this.apiFlow = apiFlow;
        }
        
        public String getFlowType() {
            return flowType;
        }
        
        public void setFlowType(String flowType) {
            this.flowType = flowType;
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
        
        public String getRequestMethod() {
            return requestMethod;
        }
        
        public void setRequestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
        }
        
        public String getRequestPath() {
            return requestPath;
        }
        
        public void setRequestPath(String requestPath) {
            this.requestPath = requestPath;
        }
        
        public Map<String, Object> getResponseBody() {
            return responseBody;
        }
        
        public void setResponseBody(Map<String, Object> responseBody) {
            this.responseBody = responseBody;
        }
        
        public Map<String, Object> getMetadata() {
            return metadata;
        }
        
        public void setMetadata(Map<String, Object> metadata) {
            this.metadata = metadata;
        }
    }
}


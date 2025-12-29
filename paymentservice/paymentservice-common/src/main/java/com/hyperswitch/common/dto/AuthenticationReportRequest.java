package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Request DTO for generating authentication reports
 */
public class AuthenticationReportRequest {
    
    @JsonProperty("time_range")
    private TimeRange timeRange;
    
    @JsonProperty("filters")
    private AuthenticationReportFilters filters;
    
    @JsonProperty("format")
    private String format; // CSV, JSON, PDF
    
    @JsonProperty("dimensions")
    private List<String> dimensions;
    
    // Getters and Setters
    public TimeRange getTimeRange() {
        return timeRange;
    }
    
    public void setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
    }
    
    public AuthenticationReportFilters getFilters() {
        return filters;
    }
    
    public void setFilters(AuthenticationReportFilters filters) {
        this.filters = filters;
    }
    
    public String getFormat() {
        return format;
    }
    
    public void setFormat(String format) {
        this.format = format;
    }
    
    public List<String> getDimensions() {
        return dimensions;
    }
    
    public void setDimensions(List<String> dimensions) {
        this.dimensions = dimensions;
    }
    
    public static class TimeRange {
        @JsonProperty("start_time")
        private String startTime;
        
        @JsonProperty("end_time")
        private String endTime;
        
        public String getStartTime() {
            return startTime;
        }
        
        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }
        
        public String getEndTime() {
            return endTime;
        }
        
        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
    
    public static class AuthenticationReportFilters {
        @JsonProperty("authentication_status")
        private List<String> authenticationStatus;
        
        @JsonProperty("authentication_type")
        private List<String> authenticationType;
        
        @JsonProperty("connector")
        private List<String> connector;
        
        public List<String> getAuthenticationStatus() {
            return authenticationStatus;
        }
        
        public void setAuthenticationStatus(List<String> authenticationStatus) {
            this.authenticationStatus = authenticationStatus;
        }
        
        public List<String> getAuthenticationType() {
            return authenticationType;
        }
        
        public void setAuthenticationType(List<String> authenticationType) {
            this.authenticationType = authenticationType;
        }
        
        public List<String> getConnector() {
            return connector;
        }
        
        public void setConnector(List<String> connector) {
            this.connector = connector;
        }
    }
}


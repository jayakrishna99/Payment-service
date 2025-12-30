package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Request DTO for generating dispute reports
 */
public class DisputeReportRequest {
    
    @JsonProperty("time_range")
    private TimeRange timeRange;
    
    @JsonProperty("filters")
    private DisputeReportFilters filters;
    
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
    
    public DisputeReportFilters getFilters() {
        return filters;
    }
    
    public void setFilters(DisputeReportFilters filters) {
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
    
    /**
     * Time range for report
     */
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
    
    /**
     * Filters for dispute report
     */
    public static class DisputeReportFilters {
        @JsonProperty("dispute_stage")
        private List<String> disputeStage;
        
        @JsonProperty("connector")
        private List<String> connector;
        
        @JsonProperty("currency")
        private List<String> currency;
        
        @JsonProperty("status")
        private List<String> status;
        
        // Getters and Setters
        public List<String> getDisputeStage() {
            return disputeStage;
        }
        
        public void setDisputeStage(List<String> disputeStage) {
            this.disputeStage = disputeStage;
        }
        
        public List<String> getConnector() {
            return connector;
        }
        
        public void setConnector(List<String> connector) {
            this.connector = connector;
        }
        
        public List<String> getCurrency() {
            return currency;
        }
        
        public void setCurrency(List<String> currency) {
            this.currency = currency;
        }
        
        public List<String> getStatus() {
            return status;
        }
        
        public void setStatus(List<String> status) {
            this.status = status;
        }
    }
}


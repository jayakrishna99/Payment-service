package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Request DTO for generating payout reports
 */
public class PayoutReportRequest {
    
    @JsonProperty("time_range")
    private TimeRange timeRange;
    
    @JsonProperty("filters")
    private PayoutReportFilters filters;
    
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
    
    public PayoutReportFilters getFilters() {
        return filters;
    }
    
    public void setFilters(PayoutReportFilters filters) {
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
    
    public static class PayoutReportFilters {
        @JsonProperty("payout_status")
        private List<String> payoutStatus;
        
        @JsonProperty("connector")
        private List<String> connector;
        
        @JsonProperty("currency")
        private List<String> currency;
        
        public List<String> getPayoutStatus() {
            return payoutStatus;
        }
        
        public void setPayoutStatus(List<String> payoutStatus) {
            this.payoutStatus = payoutStatus;
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
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Request DTO for generating payment reports
 */
public class PaymentReportRequest {
    
    @JsonProperty("time_range")
    private TimeRange timeRange;
    
    @JsonProperty("filters")
    private PaymentReportFilters filters;
    
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
    
    public PaymentReportFilters getFilters() {
        return filters;
    }
    
    public void setFilters(PaymentReportFilters filters) {
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
    
    public static class PaymentReportFilters {
        @JsonProperty("status")
        private List<String> status;
        
        @JsonProperty("connector")
        private List<String> connector;
        
        @JsonProperty("currency")
        private List<String> currency;
        
        @JsonProperty("payment_method")
        private List<String> paymentMethod;
        
        public List<String> getStatus() {
            return status;
        }
        
        public void setStatus(List<String> status) {
            this.status = status;
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
        
        public List<String> getPaymentMethod() {
            return paymentMethod;
        }
        
        public void setPaymentMethod(List<String> paymentMethod) {
            this.paymentMethod = paymentMethod;
        }
    }
}


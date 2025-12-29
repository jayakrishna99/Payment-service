package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Response DTO for sankey diagram
 */
public class SankeyResponse {
    
    @JsonProperty("query_data")
    private List<SankeyData> queryData;
    
    // Getters and Setters
    public List<SankeyData> getQueryData() {
        return queryData;
    }
    
    public void setQueryData(List<SankeyData> queryData) {
        this.queryData = queryData;
    }
    
    /**
     * Individual sankey data point
     */
    public static class SankeyData {
        @JsonProperty("count")
        private Long count;
        
        @JsonProperty("status")
        private String status;
        
        @JsonProperty("refunds_status")
        private String refundsStatus;
        
        @JsonProperty("dispute_status")
        private String disputeStatus;
        
        @JsonProperty("first_attempt")
        private Long firstAttempt;
        
        // Getters and Setters
        public Long getCount() {
            return count;
        }
        
        public void setCount(Long count) {
            this.count = count;
        }
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
        
        public String getRefundsStatus() {
            return refundsStatus;
        }
        
        public void setRefundsStatus(String refundsStatus) {
            this.refundsStatus = refundsStatus;
        }
        
        public String getDisputeStatus() {
            return disputeStatus;
        }
        
        public void setDisputeStatus(String disputeStatus) {
            this.disputeStatus = disputeStatus;
        }
        
        public Long getFirstAttempt() {
            return firstAttempt;
        }
        
        public void setFirstAttempt(Long firstAttempt) {
            this.firstAttempt = firstAttempt;
        }
    }
}


package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Response for dispute aggregates (status counts)
 */
public class DisputeAggregatesResponse {
    private Map<String, Long> statusCounts;
    private Map<String, Long> stageCounts;
    private Long totalDisputes;
    
    public DisputeAggregatesResponse() {
    }
    
    public DisputeAggregatesResponse(Map<String, Long> statusCounts, Map<String, Long> stageCounts, Long totalDisputes) {
        this.statusCounts = statusCounts;
        this.stageCounts = stageCounts;
        this.totalDisputes = totalDisputes;
    }
    
    public Map<String, Long> getStatusCounts() {
        return statusCounts;
    }
    
    public void setStatusCounts(Map<String, Long> statusCounts) {
        this.statusCounts = statusCounts;
    }
    
    public Map<String, Long> getStageCounts() {
        return stageCounts;
    }
    
    public void setStageCounts(Map<String, Long> stageCounts) {
        this.stageCounts = stageCounts;
    }
    
    public Long getTotalDisputes() {
        return totalDisputes;
    }
    
    public void setTotalDisputes(Long totalDisputes) {
        this.totalDisputes = totalDisputes;
    }
}


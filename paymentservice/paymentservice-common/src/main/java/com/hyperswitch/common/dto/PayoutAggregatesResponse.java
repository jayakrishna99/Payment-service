package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Response for payout aggregates (status counts)
 */
public class PayoutAggregatesResponse {
    private Map<String, Long> statusCounts;
    private Long totalPayouts;
    
    public PayoutAggregatesResponse() {
    }
    
    public PayoutAggregatesResponse(Map<String, Long> statusCounts, Long totalPayouts) {
        this.statusCounts = statusCounts;
        this.totalPayouts = totalPayouts;
    }
    
    public Map<String, Long> getStatusCounts() {
        return statusCounts;
    }
    
    public void setStatusCounts(Map<String, Long> statusCounts) {
        this.statusCounts = statusCounts;
    }
    
    public Long getTotalPayouts() {
        return totalPayouts;
    }
    
    public void setTotalPayouts(Long totalPayouts) {
        this.totalPayouts = totalPayouts;
    }
}


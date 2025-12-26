package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Response for refund aggregates (status counts)
 */
public class RefundAggregatesResponse {
    private Map<String, Long> statusCounts;
    private Long totalRefunds;
    
    public RefundAggregatesResponse() {
    }
    
    public RefundAggregatesResponse(Map<String, Long> statusCounts, Long totalRefunds) {
        this.statusCounts = statusCounts;
        this.totalRefunds = totalRefunds;
    }
    
    public Map<String, Long> getStatusCounts() {
        return statusCounts;
    }
    
    public void setStatusCounts(Map<String, Long> statusCounts) {
        this.statusCounts = statusCounts;
    }
    
    public Long getTotalRefunds() {
        return totalRefunds;
    }
    
    public void setTotalRefunds(Long totalRefunds) {
        this.totalRefunds = totalRefunds;
    }
}


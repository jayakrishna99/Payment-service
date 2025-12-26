package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Response for payment aggregates
 */
public class PaymentsAggregateResponse {
    private Map<String, Long> statusWithCount;
    
    public PaymentsAggregateResponse() {
    }
    
    public Map<String, Long> getStatusWithCount() {
        return statusWithCount;
    }
    
    public void setStatusWithCount(Map<String, Long> statusWithCount) {
        this.statusWithCount = statusWithCount;
    }
}


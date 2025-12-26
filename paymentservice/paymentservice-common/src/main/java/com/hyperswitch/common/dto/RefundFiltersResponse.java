package com.hyperswitch.common.dto;

import java.util.List;

/**
 * Response for available refund filters
 */
public class RefundFiltersResponse {
    private List<String> connector;
    private List<String> currency;
    private List<String> status;
    
    public RefundFiltersResponse() {
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


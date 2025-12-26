package com.hyperswitch.common.dto;

import java.util.List;

/**
 * Response for available payout filters
 */
public class PayoutFiltersResponse {
    private List<String> connector;
    private List<String> currency;
    private List<String> status;
    
    public PayoutFiltersResponse() {
    }
    
    public PayoutFiltersResponse(List<String> connector, List<String> currency, List<String> status) {
        this.connector = connector;
        this.currency = currency;
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
    
    public List<String> getStatus() {
        return status;
    }
    
    public void setStatus(List<String> status) {
        this.status = status;
    }
}


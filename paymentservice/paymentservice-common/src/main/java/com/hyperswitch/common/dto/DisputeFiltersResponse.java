package com.hyperswitch.common.dto;

import java.util.List;

/**
 * Response for available dispute filters
 */
public class DisputeFiltersResponse {
    private List<String> connector;
    private List<String> currency;
    private List<String> status;
    private List<String> stage;
    private List<String> reason;
    
    public DisputeFiltersResponse() {
    }
    
    public DisputeFiltersResponse(List<String> connector, List<String> currency, 
                                 List<String> status, List<String> stage, List<String> reason) {
        this.connector = connector;
        this.currency = currency;
        this.status = status;
        this.stage = stage;
        this.reason = reason;
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
    
    public List<String> getStage() {
        return stage;
    }
    
    public void setStage(List<String> stage) {
        this.stage = stage;
    }
    
    public List<String> getReason() {
        return reason;
    }
    
    public void setReason(List<String> reason) {
        this.reason = reason;
    }
}


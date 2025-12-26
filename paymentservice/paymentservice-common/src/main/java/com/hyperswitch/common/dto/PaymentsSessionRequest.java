package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Request for creating payment session tokens
 */
public class PaymentsSessionRequest {
    private String paymentId;
    private Map<String, Object> sessionData;
    private String connector;
    
    public PaymentsSessionRequest() {
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public Map<String, Object> getSessionData() {
        return sessionData;
    }
    
    public void setSessionData(Map<String, Object> sessionData) {
        this.sessionData = sessionData;
    }
    
    public String getConnector() {
        return connector;
    }
    
    public void setConnector(String connector) {
        this.connector = connector;
    }
}


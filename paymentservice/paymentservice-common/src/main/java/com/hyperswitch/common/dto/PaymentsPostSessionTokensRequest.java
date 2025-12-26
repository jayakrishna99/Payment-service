package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Request for posting session tokens to a payment
 */
public class PaymentsPostSessionTokensRequest {
    private String paymentId;
    private Map<String, String> sessionTokens;
    private String connector;
    
    public PaymentsPostSessionTokensRequest() {
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public Map<String, String> getSessionTokens() {
        return sessionTokens;
    }
    
    public void setSessionTokens(Map<String, String> sessionTokens) {
        this.sessionTokens = sessionTokens;
    }
    
    public String getConnector() {
        return connector;
    }
    
    public void setConnector(String connector) {
        this.connector = connector;
    }
}


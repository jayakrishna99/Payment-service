package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Response for payment session tokens
 */
public class PaymentsSessionResponse {
    private String paymentId;
    private Map<String, String> sessionTokens;
    private String status;
    private String message;
    
    public PaymentsSessionResponse() {
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
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}


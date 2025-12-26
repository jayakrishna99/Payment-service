package com.hyperswitch.common.dto;

/**
 * Response for posting session tokens
 */
public class PaymentsPostSessionTokensResponse {
    private String paymentId;
    private String status;
    private String message;
    
    public PaymentsPostSessionTokensResponse() {
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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


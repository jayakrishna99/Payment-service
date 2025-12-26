package com.hyperswitch.common.dto;

/**
 * Response for finishing payment redirection
 */
public class PaymentsFinishRedirectionResponse {
    private String paymentId;
    private String status;
    private String message;
    private PaymentsIntentResponse paymentIntent;
    
    public PaymentsFinishRedirectionResponse() {
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
    
    public PaymentsIntentResponse getPaymentIntent() {
        return paymentIntent;
    }
    
    public void setPaymentIntent(PaymentsIntentResponse paymentIntent) {
        this.paymentIntent = paymentIntent;
    }
}


package com.hyperswitch.common.dto;

/**
 * Response for starting payment redirection
 */
public class PaymentsStartRedirectionResponse {
    private String redirectUrl;
    private String paymentId;
    private String status;
    private String message;
    
    public PaymentsStartRedirectionResponse() {
    }
    
    public PaymentsStartRedirectionResponse(String redirectUrl, String paymentId, String status) {
        this.redirectUrl = redirectUrl;
        this.paymentId = paymentId;
        this.status = status;
    }
    
    public String getRedirectUrl() {
        return redirectUrl;
    }
    
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
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


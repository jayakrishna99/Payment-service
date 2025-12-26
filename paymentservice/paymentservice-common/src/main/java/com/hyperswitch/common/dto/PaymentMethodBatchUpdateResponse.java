package com.hyperswitch.common.dto;

import java.util.List;

/**
 * Response for batch payment method update
 */
public class PaymentMethodBatchUpdateResponse {
    private List<PaymentMethodUpdateResult> results;
    private Integer totalCount;
    private Integer successCount;
    private Integer failureCount;
    
    public PaymentMethodBatchUpdateResponse() {
    }
    
    public List<PaymentMethodUpdateResult> getResults() {
        return results;
    }
    
    public void setResults(List<PaymentMethodUpdateResult> results) {
        this.results = results;
    }
    
    public Integer getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    
    public Integer getSuccessCount() {
        return successCount;
    }
    
    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }
    
    public Integer getFailureCount() {
        return failureCount;
    }
    
    public void setFailureCount(Integer failureCount) {
        this.failureCount = failureCount;
    }
    
    public static class PaymentMethodUpdateResult {
        private String paymentMethodId;
        private Boolean success;
        private String errorMessage;
        private PaymentMethodResponse paymentMethod;
        
        public PaymentMethodUpdateResult() {
        }
        
        public String getPaymentMethodId() {
            return paymentMethodId;
        }
        
        public void setPaymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
        }
        
        public Boolean getSuccess() {
            return success;
        }
        
        public void setSuccess(Boolean success) {
            this.success = success;
        }
        
        public String getErrorMessage() {
            return errorMessage;
        }
        
        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
        
        public PaymentMethodResponse getPaymentMethod() {
            return paymentMethod;
        }
        
        public void setPaymentMethod(PaymentMethodResponse paymentMethod) {
            this.paymentMethod = paymentMethod;
        }
    }
}


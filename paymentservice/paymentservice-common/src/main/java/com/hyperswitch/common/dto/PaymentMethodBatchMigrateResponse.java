package com.hyperswitch.common.dto;

import java.util.List;

/**
 * Response for batch payment method migration
 */
public class PaymentMethodBatchMigrateResponse {
    private List<PaymentMethodMigrateResult> results;
    private Integer totalCount;
    private Integer successCount;
    private Integer failureCount;
    
    public PaymentMethodBatchMigrateResponse() {
    }
    
    public List<PaymentMethodMigrateResult> getResults() {
        return results;
    }
    
    public void setResults(List<PaymentMethodMigrateResult> results) {
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
    
    public static class PaymentMethodMigrateResult {
        private String paymentMethodId;
        private Boolean success;
        private String errorMessage;
        private PaymentMethodResponse paymentMethod;
        
        public PaymentMethodMigrateResult() {
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


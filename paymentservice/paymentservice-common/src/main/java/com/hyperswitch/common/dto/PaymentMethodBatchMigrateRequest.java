package com.hyperswitch.common.dto;

import java.util.List;

/**
 * Request for batch migrating payment methods
 */
public class PaymentMethodBatchMigrateRequest {
    private List<PaymentMethodMigrateRequest> paymentMethods;
    private String merchantId;
    
    public PaymentMethodBatchMigrateRequest() {
    }
    
    public List<PaymentMethodMigrateRequest> getPaymentMethods() {
        return paymentMethods;
    }
    
    public void setPaymentMethods(List<PaymentMethodMigrateRequest> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}


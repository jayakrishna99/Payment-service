package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Request for migrating a payment method from one connector to another
 */
public class PaymentMethodMigrateRequest {
    private String paymentMethodId;
    private String customerId;
    private String fromConnector;
    private String toConnector;
    private Map<String, Object> metadata;
    
    public PaymentMethodMigrateRequest() {
    }
    
    public String getPaymentMethodId() {
        return paymentMethodId;
    }
    
    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getFromConnector() {
        return fromConnector;
    }
    
    public void setFromConnector(String fromConnector) {
        this.fromConnector = fromConnector;
    }
    
    public String getToConnector() {
        return toConnector;
    }
    
    public void setToConnector(String toConnector) {
        this.toConnector = toConnector;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}


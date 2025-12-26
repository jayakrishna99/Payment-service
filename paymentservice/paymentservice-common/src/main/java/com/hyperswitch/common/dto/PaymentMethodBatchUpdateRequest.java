package com.hyperswitch.common.dto;

import java.util.List;
import java.util.Map;

/**
 * Request for batch updating payment methods
 */
public class PaymentMethodBatchUpdateRequest {
    private List<PaymentMethodUpdateItem> paymentMethods;
    private String merchantId;
    
    public PaymentMethodBatchUpdateRequest() {
    }
    
    public List<PaymentMethodUpdateItem> getPaymentMethods() {
        return paymentMethods;
    }
    
    public void setPaymentMethods(List<PaymentMethodUpdateItem> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public static class PaymentMethodUpdateItem {
        private String paymentMethodId;
        private Map<String, Object> paymentMethodData;
        private String networkTransactionId;
        private Map<String, Object> connectorMandateDetails;
        private Map<String, Object> metadata;
        
        public PaymentMethodUpdateItem() {
        }
        
        public String getPaymentMethodId() {
            return paymentMethodId;
        }
        
        public void setPaymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
        }
        
        public Map<String, Object> getPaymentMethodData() {
            return paymentMethodData;
        }
        
        public void setPaymentMethodData(Map<String, Object> paymentMethodData) {
            this.paymentMethodData = paymentMethodData;
        }
        
        public String getNetworkTransactionId() {
            return networkTransactionId;
        }
        
        public void setNetworkTransactionId(String networkTransactionId) {
            this.networkTransactionId = networkTransactionId;
        }
        
        public Map<String, Object> getConnectorMandateDetails() {
            return connectorMandateDetails;
        }
        
        public void setConnectorMandateDetails(Map<String, Object> connectorMandateDetails) {
            this.connectorMandateDetails = connectorMandateDetails;
        }
        
        public Map<String, Object> getMetadata() {
            return metadata;
        }
        
        public void setMetadata(Map<String, Object> metadata) {
            this.metadata = metadata;
        }
    }
}


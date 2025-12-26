package com.hyperswitch.common.dto;

import java.util.List;
import java.util.Map;

/**
 * Response for payment method filters
 */
public class PaymentMethodFilterResponse {
    private Map<String, List<ConnectorPaymentMethodInfo>> connector;
    
    public PaymentMethodFilterResponse() {
    }
    
    public Map<String, List<ConnectorPaymentMethodInfo>> getConnector() {
        return connector;
    }
    
    public void setConnector(Map<String, List<ConnectorPaymentMethodInfo>> connector) {
        this.connector = connector;
    }
    
    public static class ConnectorPaymentMethodInfo {
        private String paymentMethod;
        private String paymentMethodType;
        private List<String> currencies;
        private List<String> countries;
        
        public ConnectorPaymentMethodInfo() {
        }
        
        public String getPaymentMethod() {
            return paymentMethod;
        }
        
        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }
        
        public String getPaymentMethodType() {
            return paymentMethodType;
        }
        
        public void setPaymentMethodType(String paymentMethodType) {
            this.paymentMethodType = paymentMethodType;
        }
        
        public List<String> getCurrencies() {
            return currencies;
        }
        
        public void setCurrencies(List<String> currencies) {
            this.currencies = currencies;
        }
        
        public List<String> getCountries() {
            return countries;
        }
        
        public void setCountries(List<String> countries) {
            this.countries = countries;
        }
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Response DTO for feature matrix
 */
public class FeatureMatrixResponse {
    
    @JsonProperty("connectors")
    private Map<String, ConnectorFeatures> connectors;
    
    // Getters and Setters
    public Map<String, ConnectorFeatures> getConnectors() {
        return connectors;
    }
    
    public void setConnectors(Map<String, ConnectorFeatures> connectors) {
        this.connectors = connectors;
    }
    
    /**
     * Connector features structure
     */
    public static class ConnectorFeatures {
        @JsonProperty("payment_methods")
        private java.util.List<String> paymentMethods;
        
        @JsonProperty("currencies")
        private java.util.List<String> currencies;
        
        @JsonProperty("countries")
        private java.util.List<String> countries;
        
        @JsonProperty("features")
        private Map<String, Boolean> features;
        
        @JsonProperty("capabilities")
        private Map<String, Boolean> capabilities;
        
        // Getters and Setters
        public java.util.List<String> getPaymentMethods() {
            return paymentMethods;
        }
        
        public void setPaymentMethods(java.util.List<String> paymentMethods) {
            this.paymentMethods = paymentMethods;
        }
        
        public java.util.List<String> getCurrencies() {
            return currencies;
        }
        
        public void setCurrencies(java.util.List<String> currencies) {
            this.currencies = currencies;
        }
        
        public java.util.List<String> getCountries() {
            return countries;
        }
        
        public void setCountries(java.util.List<String> countries) {
            this.countries = countries;
        }
        
        public Map<String, Boolean> getFeatures() {
            return features;
        }
        
        public void setFeatures(Map<String, Boolean> features) {
            this.features = features;
        }
        
        public Map<String, Boolean> getCapabilities() {
            return capabilities;
        }
        
        public void setCapabilities(Map<String, Boolean> capabilities) {
            this.capabilities = capabilities;
        }
    }
}


package com.hyperswitch.common.dto;

import java.util.List;
import java.util.Map;

/**
 * Response for available payment filters
 */
public class PaymentListFiltersResponse {
    private Map<String, List<MerchantConnectorInfo>> connector;
    private List<String> currency;
    private List<String> status;
    private Map<String, List<String>> paymentMethod;
    private List<String> authenticationType;
    private List<String> cardNetwork;
    private List<String> cardDiscovery;
    
    public PaymentListFiltersResponse() {
    }
    
    // Getters and Setters
    public Map<String, List<MerchantConnectorInfo>> getConnector() {
        return connector;
    }
    
    public void setConnector(Map<String, List<MerchantConnectorInfo>> connector) {
        this.connector = connector;
    }
    
    public List<String> getCurrency() {
        return currency;
    }
    
    public void setCurrency(List<String> currency) {
        this.currency = currency;
    }
    
    public List<String> getStatus() {
        return status;
    }
    
    public void setStatus(List<String> status) {
        this.status = status;
    }
    
    public Map<String, List<String>> getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(Map<String, List<String>> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public List<String> getAuthenticationType() {
        return authenticationType;
    }
    
    public void setAuthenticationType(List<String> authenticationType) {
        this.authenticationType = authenticationType;
    }
    
    public List<String> getCardNetwork() {
        return cardNetwork;
    }
    
    public void setCardNetwork(List<String> cardNetwork) {
        this.cardNetwork = cardNetwork;
    }
    
    public List<String> getCardDiscovery() {
        return cardDiscovery;
    }
    
    public void setCardDiscovery(List<String> cardDiscovery) {
        this.cardDiscovery = cardDiscovery;
    }
}


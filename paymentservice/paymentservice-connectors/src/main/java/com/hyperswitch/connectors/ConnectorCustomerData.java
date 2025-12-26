package com.hyperswitch.connectors;

import java.util.Map;

/**
 * Data structure for creating a customer on a connector
 */
public class ConnectorCustomerData {
    private String email;
    private String phone;
    private String name;
    private String description;
    private Map<String, Object> metadata;
    private Map<String, Object> billingAddress;
    
    // Getters and Setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    public Map<String, Object> getBillingAddress() {
        return billingAddress;
    }
    
    public void setBillingAddress(Map<String, Object> billingAddress) {
        this.billingAddress = billingAddress;
    }
}


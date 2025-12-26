package com.hyperswitch.common.dto;

import java.time.Instant;
import java.util.List;

/**
 * Filter constraints for payment listing
 */
public class PaymentListFilterConstraints {
    private String paymentId;
    private String profileId;
    private String customerId;
    private Integer limit = 10;
    private Integer offset = 0;
    private AmountFilter amountFilter;
    private Instant startTime;
    private Instant endTime;
    private List<String> connector;
    private List<String> currency;
    private List<String> status;
    private List<String> paymentMethod;
    private List<String> paymentMethodType;
    private List<String> authenticationType;
    private List<String> merchantConnectorId;
    private Order order;
    
    public PaymentListFilterConstraints() {
        this.order = new Order();
    }
    
    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public String getProfileId() {
        return profileId;
    }
    
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public Integer getLimit() {
        return limit;
    }
    
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    
    public Integer getOffset() {
        return offset;
    }
    
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    
    public AmountFilter getAmountFilter() {
        return amountFilter;
    }
    
    public void setAmountFilter(AmountFilter amountFilter) {
        this.amountFilter = amountFilter;
    }
    
    public Instant getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }
    
    public Instant getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }
    
    public List<String> getConnector() {
        return connector;
    }
    
    public void setConnector(List<String> connector) {
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
    
    public List<String> getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(List<String> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public List<String> getPaymentMethodType() {
        return paymentMethodType;
    }
    
    public void setPaymentMethodType(List<String> paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }
    
    public List<String> getAuthenticationType() {
        return authenticationType;
    }
    
    public void setAuthenticationType(List<String> authenticationType) {
        this.authenticationType = authenticationType;
    }
    
    public List<String> getMerchantConnectorId() {
        return merchantConnectorId;
    }
    
    public void setMerchantConnectorId(List<String> merchantConnectorId) {
        this.merchantConnectorId = merchantConnectorId;
    }
    
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
}


package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * Entity for logging routing decisions for analytics
 */
@Table("routing_decision_log")
public class RoutingDecisionLogEntity {
    
    @Id
    @Column("id")
    private String id;
    
    @Column("payment_id")
    private String paymentId;
    
    @Column("attempt_id")
    private String attemptId;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("profile_id")
    private String profileId;
    
    @Column("selected_connector")
    private String selectedConnector;
    
    @Column("routing_algorithm")
    private String routingAlgorithm;
    
    @Column("amount")
    private Long amount;
    
    @Column("currency")
    private String currency;
    
    @Column("payment_method")
    private String paymentMethod;
    
    @Column("success")
    private Boolean success;
    
    @Column("created_at")
    private Instant createdAt;
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public String getAttemptId() {
        return attemptId;
    }
    
    public void setAttemptId(String attemptId) {
        this.attemptId = attemptId;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getProfileId() {
        return profileId;
    }
    
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
    
    public String getSelectedConnector() {
        return selectedConnector;
    }
    
    public void setSelectedConnector(String selectedConnector) {
        this.selectedConnector = selectedConnector;
    }
    
    public String getRoutingAlgorithm() {
        return routingAlgorithm;
    }
    
    public void setRoutingAlgorithm(String routingAlgorithm) {
        this.routingAlgorithm = routingAlgorithm;
    }
    
    public Long getAmount() {
        return amount;
    }
    
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public Boolean getSuccess() {
        return success;
    }
    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public Instant getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}


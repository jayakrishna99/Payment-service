package com.hyperswitch.common.dto;

import java.time.Instant;
import java.util.Map;

/**
 * Payment attempt response for payment intent
 */
public class PaymentAttemptResponse {
    private String id;
    private String attemptId;
    private String paymentId;
    private String merchantId;
    private String status;
    private String connector;
    private String connectorTransactionId;
    private Long amount;
    private String currency;
    private String paymentMethod;
    private Instant createdAt;
    private Instant modifiedAt;
    private Map<String, Object> connectorMetadata;
    private Map<String, Object> metadata;
    private String errorMessage;
    private String errorCode;
    
    public PaymentAttemptResponse() {
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getAttemptId() {
        return attemptId;
    }
    
    public void setAttemptId(String attemptId) {
        this.attemptId = attemptId;
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getConnector() {
        return connector;
    }
    
    public void setConnector(String connector) {
        this.connector = connector;
    }
    
    public String getConnectorTransactionId() {
        return connectorTransactionId;
    }
    
    public void setConnectorTransactionId(String connectorTransactionId) {
        this.connectorTransactionId = connectorTransactionId;
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
    
    public Instant getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    
    public Instant getModifiedAt() {
        return modifiedAt;
    }
    
    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
    
    public Map<String, Object> getConnectorMetadata() {
        return connectorMetadata;
    }
    
    public void setConnectorMetadata(Map<String, Object> connectorMetadata) {
        this.connectorMetadata = connectorMetadata;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

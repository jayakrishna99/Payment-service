package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.Amount;
import java.util.Map;

/**
 * Request DTO for creating a refund (v2 API)
 */
public class RefundsCreateRequest {
    private String paymentId;
    private String merchantReferenceId;
    private String merchantId;
    private Amount amount;
    private String reason;
    private String refundType; // "Instant" or "Scheduled"
    private Map<String, Object> metadata;
    private MerchantConnectorAuthDetails merchantConnectorDetails;
    private Boolean returnRawConnectorResponse;
    
    public RefundsCreateRequest() {
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public String getMerchantReferenceId() {
        return merchantReferenceId;
    }
    
    public void setMerchantReferenceId(String merchantReferenceId) {
        this.merchantReferenceId = merchantReferenceId;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public Amount getAmount() {
        return amount;
    }
    
    public void setAmount(Amount amount) {
        this.amount = amount;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public String getRefundType() {
        return refundType;
    }
    
    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    public MerchantConnectorAuthDetails getMerchantConnectorDetails() {
        return merchantConnectorDetails;
    }
    
    public void setMerchantConnectorDetails(MerchantConnectorAuthDetails merchantConnectorDetails) {
        this.merchantConnectorDetails = merchantConnectorDetails;
    }
    
    public Boolean getReturnRawConnectorResponse() {
        return returnRawConnectorResponse;
    }
    
    public void setReturnRawConnectorResponse(Boolean returnRawConnectorResponse) {
        this.returnRawConnectorResponse = returnRawConnectorResponse;
    }
}


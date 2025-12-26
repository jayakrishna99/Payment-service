package com.hyperswitch.common.dto;

/**
 * Request for refund sync
 */
public class SyncRefundRequest {
    private String refundId;
    private String paymentId;
    private String merchantId;
    private Boolean forceSync;
    
    public SyncRefundRequest() {
    }
    
    public String getRefundId() {
        return refundId;
    }
    
    public void setRefundId(String refundId) {
        this.refundId = refundId;
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
    
    public Boolean getForceSync() {
        return forceSync;
    }
    
    public void setForceSync(Boolean forceSync) {
        this.forceSync = forceSync;
    }
}


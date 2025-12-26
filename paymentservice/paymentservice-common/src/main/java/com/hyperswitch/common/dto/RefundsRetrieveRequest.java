package com.hyperswitch.common.dto;

/**
 * Request DTO for retrieving a refund (v2 API)
 */
public class RefundsRetrieveRequest {
    private String refundId;
    private Boolean forceSync;
    private MerchantConnectorAuthDetails merchantConnectorDetails;
    private Boolean returnRawConnectorResponse;
    
    public RefundsRetrieveRequest() {
    }
    
    public String getRefundId() {
        return refundId;
    }
    
    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
    
    public Boolean getForceSync() {
        return forceSync;
    }
    
    public void setForceSync(Boolean forceSync) {
        this.forceSync = forceSync;
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


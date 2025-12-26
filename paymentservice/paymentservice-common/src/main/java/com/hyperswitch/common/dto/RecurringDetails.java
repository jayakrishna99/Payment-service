package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Details required for recurring payment (MIT - Merchant Initiated Transaction)
 * Matches Hyperswitch RecurringDetails enum
 */
public class RecurringDetails {
    
    @JsonProperty("type")
    private String type;
    
    // For mandate_id type
    @JsonProperty("data")
    private String data; // Can be mandateId, paymentMethodId, or processorPaymentToken
    
    // For processor_payment_token type
    private String merchantConnectorId;

    public RecurringDetails() {
    }

    public RecurringDetails(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public RecurringDetails(String type, String data, String merchantConnectorId) {
        this.type = type;
        this.data = data;
        this.merchantConnectorId = merchantConnectorId;
    }

    public static RecurringDetails mandateId(String mandateId) {
        return new RecurringDetails("mandate_id", mandateId);
    }

    public static RecurringDetails paymentMethodId(String paymentMethodId) {
        return new RecurringDetails("payment_method_id", paymentMethodId);
    }

    public static RecurringDetails processorPaymentToken(String processorPaymentToken, String merchantConnectorId) {
        return new RecurringDetails("processor_payment_token", processorPaymentToken, merchantConnectorId);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMerchantConnectorId() {
        return merchantConnectorId;
    }

    public void setMerchantConnectorId(String merchantConnectorId) {
        this.merchantConnectorId = merchantConnectorId;
    }

    public String getMandateId() {
        return "mandate_id".equals(type) ? data : null;
    }

    public String getPaymentMethodId() {
        return "payment_method_id".equals(type) ? data : null;
    }

    public String getProcessorPaymentToken() {
        return "processor_payment_token".equals(type) ? data : null;
    }
}


package com.hyperswitch.common.dto;

import java.util.List;

/**
 * Request for batch tokenizing cards
 */
public class BatchTokenizeCardRequest {
    private List<TokenizeCardRequest> cards;
    private String merchantId;
    private String customerId;
    
    public BatchTokenizeCardRequest() {
    }
    
    public List<TokenizeCardRequest> getCards() {
        return cards;
    }
    
    public void setCards(List<TokenizeCardRequest> cards) {
        this.cards = cards;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}


package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Request for card tokenization
 */
public class TokenizeCardRequest {
    private String customerId;
    private String merchantId;
    private CardData card;
    private Boolean enableNetworkTokenization;
    private Map<String, Object> metadata;
    
    public TokenizeCardRequest() {
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public CardData getCard() {
        return card;
    }
    
    public void setCard(CardData card) {
        this.card = card;
    }
    
    public Boolean getEnableNetworkTokenization() {
        return enableNetworkTokenization;
    }
    
    public void setEnableNetworkTokenization(Boolean enableNetworkTokenization) {
        this.enableNetworkTokenization = enableNetworkTokenization;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    public static class CardData {
        private String cardNumber;
        private Integer expiryMonth;
        private Integer expiryYear;
        private String cardHolderName;
        private String cvc;
        private String cardNetwork;
        
        public CardData() {
        }
        
        public String getCardNumber() {
            return cardNumber;
        }
        
        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }
        
        public Integer getExpiryMonth() {
            return expiryMonth;
        }
        
        public void setExpiryMonth(Integer expiryMonth) {
            this.expiryMonth = expiryMonth;
        }
        
        public Integer getExpiryYear() {
            return expiryYear;
        }
        
        public void setExpiryYear(Integer expiryYear) {
            this.expiryYear = expiryYear;
        }
        
        public String getCardHolderName() {
            return cardHolderName;
        }
        
        public void setCardHolderName(String cardHolderName) {
            this.cardHolderName = cardHolderName;
        }
        
        public String getCvc() {
            return cvc;
        }
        
        public void setCvc(String cvc) {
            this.cvc = cvc;
        }
        
        public String getCardNetwork() {
            return cardNetwork;
        }
        
        public void setCardNetwork(String cardNetwork) {
            this.cardNetwork = cardNetwork;
        }
    }
}


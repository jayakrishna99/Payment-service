package com.hyperswitch.common.dto;

/**
 * Order details with amount for payment intent
 */
public class OrderDetailsWithAmount {
    private String productName;
    private Integer quantity;
    private Long amount;
    private String productImgLink;
    
    public OrderDetailsWithAmount() {
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public Long getAmount() {
        return amount;
    }
    
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    
    public String getProductImgLink() {
        return productImgLink;
    }
    
    public void setProductImgLink(String productImgLink) {
        this.productImgLink = productImgLink;
    }
}


package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Response DTO for subscription estimate
 */
@Schema(description = "Response for subscription estimate")
public class SubscriptionEstimateResponse {
    
    @Schema(description = "Estimated amount in minor units", example = "1000")
    @JsonProperty("amount")
    private Long amount;
    
    @Schema(description = "Currency code", example = "USD")
    @JsonProperty("currency")
    private String currency;
    
    @Schema(description = "Plan ID", example = "plan_123")
    @JsonProperty("plan_id")
    private String planId;
    
    @Schema(description = "Item price ID", example = "price_123")
    @JsonProperty("item_price_id")
    private String itemPriceId;
    
    @Schema(description = "Coupon code", example = "SAVE10")
    @JsonProperty("coupon_code")
    private String couponCode;
    
    @Schema(description = "Customer ID", example = "cust_123")
    @JsonProperty("customer_id")
    private String customerId;
    
    @Schema(description = "Line items for the estimate")
    @JsonProperty("line_items")
    private List<SubscriptionLineItem> lineItems;

    // Getters and Setters
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

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getItemPriceId() {
        return itemPriceId;
    }

    public void setItemPriceId(String itemPriceId) {
        this.itemPriceId = itemPriceId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<SubscriptionLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<SubscriptionLineItem> lineItems) {
        this.lineItems = lineItems;
    }
    
    /**
     * Subscription line item structure
     */
    public static class SubscriptionLineItem {
        @Schema(description = "Item ID", example = "item_123")
        @JsonProperty("item_id")
        private String itemId;
        
        @Schema(description = "Item type", example = "plan")
        @JsonProperty("item_type")
        private String itemType;
        
        @Schema(description = "Item description")
        @JsonProperty("description")
        private String description;
        
        @Schema(description = "Amount in minor units", example = "1000")
        @JsonProperty("amount")
        private Long amount;
        
        @Schema(description = "Currency code", example = "USD")
        @JsonProperty("currency")
        private String currency;
        
        @Schema(description = "Quantity", example = "1")
        @JsonProperty("quantity")
        private Long quantity;

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public Long getQuantity() {
            return quantity;
        }

        public void setQuantity(Long quantity) {
            this.quantity = quantity;
        }
    }
}


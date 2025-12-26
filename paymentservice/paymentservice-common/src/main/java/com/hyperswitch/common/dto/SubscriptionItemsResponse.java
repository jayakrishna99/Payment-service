package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Response DTO for subscription items
 */
@Schema(description = "Response for subscription items")
public class SubscriptionItemsResponse {
    
    @Schema(description = "Item ID", example = "item_123")
    @JsonProperty("item_id")
    private String itemId;
    
    @Schema(description = "Item name", example = "Premium Plan")
    @JsonProperty("name")
    private String name;
    
    @Schema(description = "Item description")
    @JsonProperty("description")
    private String description;
    
    @Schema(description = "List of prices for this item")
    @JsonProperty("price_id")
    private List<SubscriptionItemPrice> prices;

    // Getters and Setters
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubscriptionItemPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<SubscriptionItemPrice> prices) {
        this.prices = prices;
    }
    
    /**
     * Subscription item price structure
     */
    public static class SubscriptionItemPrice {
        @Schema(description = "Price ID", example = "price_123")
        @JsonProperty("price_id")
        private String priceId;
        
        @Schema(description = "Item ID", example = "item_123")
        @JsonProperty("item_id")
        private String itemId;
        
        @Schema(description = "Amount in minor units", example = "1000")
        @JsonProperty("amount")
        private Long amount;
        
        @Schema(description = "Currency code", example = "USD")
        @JsonProperty("currency")
        private String currency;
        
        @Schema(description = "Billing interval unit", example = "month")
        @JsonProperty("interval")
        private String interval;
        
        @Schema(description = "Billing interval count", example = "1")
        @JsonProperty("interval_count")
        private Long intervalCount;
        
        @Schema(description = "Trial period in days")
        @JsonProperty("trial_period")
        private Long trialPeriod;
        
        @Schema(description = "Trial period unit", example = "day")
        @JsonProperty("trial_period_unit")
        private String trialPeriodUnit;

        public String getPriceId() {
            return priceId;
        }

        public void setPriceId(String priceId) {
            this.priceId = priceId;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
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

        public String getInterval() {
            return interval;
        }

        public void setInterval(String interval) {
            this.interval = interval;
        }

        public Long getIntervalCount() {
            return intervalCount;
        }

        public void setIntervalCount(Long intervalCount) {
            this.intervalCount = intervalCount;
        }

        public Long getTrialPeriod() {
            return trialPeriod;
        }

        public void setTrialPeriod(Long trialPeriod) {
            this.trialPeriod = trialPeriod;
        }

        public String getTrialPeriodUnit() {
            return trialPeriodUnit;
        }

        public void setTrialPeriodUnit(String trialPeriodUnit) {
            this.trialPeriodUnit = trialPeriodUnit;
        }
    }
}


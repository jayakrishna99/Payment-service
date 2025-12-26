package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

/**
 * Response DTO for tax calculation
 */
@Schema(description = "Response for tax calculation")
public class TaxCalculationResponse {
    
    @Schema(description = "Total tax amount in minor units", example = "100")
    @JsonProperty("total_tax")
    private Long totalTax;
    
    @Schema(description = "Currency code", example = "USD")
    @JsonProperty("currency")
    private String currency;
    
    @Schema(description = "Tax breakdown by jurisdiction")
    @JsonProperty("tax_breakdown")
    private List<TaxBreakdown> taxBreakdown;
    
    @Schema(description = "Additional tax information")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    // Getters and Setters
    public Long getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Long totalTax) {
        this.totalTax = totalTax;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<TaxBreakdown> getTaxBreakdown() {
        return taxBreakdown;
    }

    public void setTaxBreakdown(List<TaxBreakdown> taxBreakdown) {
        this.taxBreakdown = taxBreakdown;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    /**
     * Tax breakdown structure
     */
    public static class TaxBreakdown {
        @Schema(description = "Jurisdiction name", example = "CA")
        @JsonProperty("jurisdiction")
        private String jurisdiction;
        
        @Schema(description = "Tax amount in minor units", example = "50")
        @JsonProperty("amount")
        private Long amount;
        
        @Schema(description = "Tax rate", example = "0.08")
        @JsonProperty("rate")
        private Double rate;

        public String getJurisdiction() {
            return jurisdiction;
        }

        public void setJurisdiction(String jurisdiction) {
            this.jurisdiction = jurisdiction;
        }

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }
    }
}


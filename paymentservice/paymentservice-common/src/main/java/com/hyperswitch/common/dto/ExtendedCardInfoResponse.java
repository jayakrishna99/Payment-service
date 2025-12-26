package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Response DTO for extended card information
 */
@Schema(description = "Response for extended card information")
public class ExtendedCardInfoResponse {
    
    @Schema(description = "Card last 4 digits", example = "4242")
    @JsonProperty("last4")
    private String last4;
    
    @Schema(description = "Card brand", example = "visa")
    @JsonProperty("brand")
    private String brand;
    
    @Schema(description = "Card expiry month", example = "12")
    @JsonProperty("exp_month")
    private Integer expMonth;
    
    @Schema(description = "Card expiry year", example = "2025")
    @JsonProperty("exp_year")
    private Integer expYear;
    
    @Schema(description = "Card holder name", example = "John Doe")
    @JsonProperty("cardholder_name")
    private String cardholderName;
    
    @Schema(description = "Card funding type", example = "credit")
    @JsonProperty("funding")
    private String funding;
    
    @Schema(description = "Card country", example = "US")
    @JsonProperty("country")
    private String country;
    
    @Schema(description = "Additional card information")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    // Getters and Setters
    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getFunding() {
        return funding;
    }

    public void setFunding(String funding) {
        this.funding = funding;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}


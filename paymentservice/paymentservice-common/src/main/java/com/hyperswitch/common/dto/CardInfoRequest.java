package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for card info operations
 */
@Schema(description = "Request for card info operations")
public class CardInfoRequest {
    
    @Schema(description = "Card IIN (Issuer Identification Number)", example = "411111", required = true)
    @JsonProperty("card_iin")
    private String cardIin;
    
    @Schema(description = "Card issuer", example = "Visa")
    @JsonProperty("card_issuer")
    private String cardIssuer;
    
    @Schema(description = "Card network", example = "VISA")
    @JsonProperty("card_network")
    private String cardNetwork;
    
    @Schema(description = "Card type", example = "credit")
    @JsonProperty("card_type")
    private String cardType;
    
    @Schema(description = "Card subtype", example = "consumer")
    @JsonProperty("card_subtype")
    private String cardSubtype;
    
    @Schema(description = "Card issuing country", example = "US")
    @JsonProperty("card_issuing_country")
    private String cardIssuingCountry;
    
    @Schema(description = "Bank code ID", example = "12345")
    @JsonProperty("bank_code_id")
    private String bankCodeId;
    
    @Schema(description = "Bank code", example = "CHASE")
    @JsonProperty("bank_code")
    private String bankCode;
    
    @Schema(description = "Country code", example = "US")
    @JsonProperty("country_code")
    private String countryCode;
    
    @Schema(description = "Last updated provider", example = "binlist")
    @JsonProperty("last_updated_provider")
    private String lastUpdatedProvider;

    // Getters and Setters
    public String getCardIin() {
        return cardIin;
    }

    public void setCardIin(String cardIin) {
        this.cardIin = cardIin;
    }

    public String getCardIssuer() {
        return cardIssuer;
    }

    public void setCardIssuer(String cardIssuer) {
        this.cardIssuer = cardIssuer;
    }

    public String getCardNetwork() {
        return cardNetwork;
    }

    public void setCardNetwork(String cardNetwork) {
        this.cardNetwork = cardNetwork;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardSubtype() {
        return cardSubtype;
    }

    public void setCardSubtype(String cardSubtype) {
        this.cardSubtype = cardSubtype;
    }

    public String getCardIssuingCountry() {
        return cardIssuingCountry;
    }

    public void setCardIssuingCountry(String cardIssuingCountry) {
        this.cardIssuingCountry = cardIssuingCountry;
    }

    public String getBankCodeId() {
        return bankCodeId;
    }

    public void setBankCodeId(String bankCodeId) {
        this.bankCodeId = bankCodeId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLastUpdatedProvider() {
        return lastUpdatedProvider;
    }

    public void setLastUpdatedProvider(String lastUpdatedProvider) {
        this.lastUpdatedProvider = lastUpdatedProvider;
    }
}


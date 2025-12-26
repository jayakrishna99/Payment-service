package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

/**
 * Response DTO for card info operations
 */
@Schema(description = "Response for card info operations")
public class CardInfoResponse {
    
    @Schema(description = "Card IIN", example = "411111")
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
    
    @Schema(description = "Date created")
    @JsonProperty("date_created")
    private Instant dateCreated;
    
    @Schema(description = "Last updated")
    @JsonProperty("last_updated")
    private Instant lastUpdated;
    
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

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedProvider() {
        return lastUpdatedProvider;
    }

    public void setLastUpdatedProvider(String lastUpdatedProvider) {
        this.lastUpdatedProvider = lastUpdatedProvider;
    }
}


package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * Card info entity matching Hyperswitch schema
 */
@Table("cards_info")
public class CardInfoEntity {
    @Id
    @Column("card_iin")
    private String cardIin;
    
    @Column("card_issuer")
    private String cardIssuer;
    
    @Column("card_network")
    private String cardNetwork;
    
    @Column("card_type")
    private String cardType;
    
    @Column("card_subtype")
    private String cardSubtype;
    
    @Column("card_issuing_country")
    private String cardIssuingCountry;
    
    @Column("bank_code_id")
    private String bankCodeId;
    
    @Column("bank_code")
    private String bankCode;
    
    @Column("country_code")
    private String countryCode;
    
    @Column("date_created")
    private Instant dateCreated;
    
    @Column("last_updated")
    private Instant lastUpdated;
    
    @Column("last_updated_provider")
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


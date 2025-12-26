package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for executing 3DS decision rule
 */
@Schema(description = "Request for executing 3DS decision rule")
public class ThreeDsDecisionRuleExecuteRequest {
    
    @Schema(description = "The ID of the routing algorithm to be executed", required = true)
    @JsonProperty("routing_id")
    private String routingId;
    
    @Schema(description = "Data related to the payment", required = true)
    @JsonProperty("payment")
    private PaymentData payment;
    
    @Schema(description = "Optional metadata about the payment method")
    @JsonProperty("payment_method")
    private PaymentMethodMetaData paymentMethod;
    
    @Schema(description = "Optional data about the customer's device")
    @JsonProperty("customer_device")
    private CustomerDeviceData customerDevice;
    
    @Schema(description = "Optional data about the issuer")
    @JsonProperty("issuer")
    private IssuerData issuer;
    
    @Schema(description = "Optional data about the acquirer")
    @JsonProperty("acquirer")
    private AcquirerData acquirer;

    // Getters and Setters
    public String getRoutingId() {
        return routingId;
    }

    public void setRoutingId(String routingId) {
        this.routingId = routingId;
    }

    public PaymentData getPayment() {
        return payment;
    }

    public void setPayment(PaymentData payment) {
        this.payment = payment;
    }

    public PaymentMethodMetaData getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodMetaData paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CustomerDeviceData getCustomerDevice() {
        return customerDevice;
    }

    public void setCustomerDevice(CustomerDeviceData customerDevice) {
        this.customerDevice = customerDevice;
    }

    public IssuerData getIssuer() {
        return issuer;
    }

    public void setIssuer(IssuerData issuer) {
        this.issuer = issuer;
    }

    public AcquirerData getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(AcquirerData acquirer) {
        this.acquirer = acquirer;
    }
    
    // Inner classes for nested objects
    @Schema(description = "Payment data")
    public static class PaymentData {
        @Schema(description = "The amount of the payment in minor units", example = "1000", required = true)
        @JsonProperty("amount")
        private Long amount;
        
        @Schema(description = "The currency of the payment", example = "USD", required = true)
        @JsonProperty("currency")
        private String currency;

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
    }
    
    @Schema(description = "Payment method metadata")
    public static class PaymentMethodMetaData {
        @Schema(description = "The card network", example = "Visa")
        @JsonProperty("card_network")
        private String cardNetwork;

        public String getCardNetwork() {
            return cardNetwork;
        }

        public void setCardNetwork(String cardNetwork) {
            this.cardNetwork = cardNetwork;
        }
    }
    
    @Schema(description = "Customer device data")
    public static class CustomerDeviceData {
        @Schema(description = "The platform of the customer's device", example = "web")
        @JsonProperty("platform")
        private String platform;
        
        @Schema(description = "The type of the customer's device", example = "desktop")
        @JsonProperty("device_type")
        private String deviceType;
        
        @Schema(description = "The display size of the customer's device", example = "large")
        @JsonProperty("display_size")
        private String displaySize;

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getDisplaySize() {
            return displaySize;
        }

        public void setDisplaySize(String displaySize) {
            this.displaySize = displaySize;
        }
    }
    
    @Schema(description = "Issuer data")
    public static class IssuerData {
        @Schema(description = "The name of the issuer", example = "Chase Bank")
        @JsonProperty("name")
        private String name;
        
        @Schema(description = "The country of the issuer", example = "US")
        @JsonProperty("country")
        private String country;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
    
    @Schema(description = "Acquirer data")
    public static class AcquirerData {
        @Schema(description = "The country of the acquirer", example = "US")
        @JsonProperty("country")
        private String country;
        
        @Schema(description = "The fraud rate associated with the acquirer", example = "0.01")
        @JsonProperty("fraud_rate")
        private Double fraudRate;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Double getFraudRate() {
            return fraudRate;
        }

        public void setFraudRate(Double fraudRate) {
            this.fraudRate = fraudRate;
        }
    }
}


package com.hyperswitch.common.types;

/**
 * Enum for analytics domains
 */
public enum AnalyticsDomain {
    PAYMENTS("payments"),
    PAYMENT_INTENTS("payment_intents"),
    REFUNDS("refunds"),
    ROUTING("routing"),
    AUTH_EVENTS("auth_events"),
    SDK_EVENTS("sdk_events"),
    FRM("frm"),
    DISPUTE("dispute"),
    API_EVENTS("api_events");
    
    private final String value;
    
    AnalyticsDomain(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static AnalyticsDomain fromString(String value) {
        for (AnalyticsDomain domain : AnalyticsDomain.values()) {
            if (domain.value.equalsIgnoreCase(value)) {
                return domain;
            }
        }
        throw new IllegalArgumentException("Unknown analytics domain: " + value);
    }
}


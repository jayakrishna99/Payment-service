package com.hyperswitch.common.types;

/**
 * Enum for 3DS decision outcomes
 */
public enum ThreeDSDecision {
    NO_THREE_DS("no_three_ds"),
    CHALLENGE_REQUESTED("challenge_requested"),
    CHALLENGE_PREFERRED("challenge_preferred"),
    THREE_DS_EXEMPTION_REQUESTED_TRA("three_ds_exemption_requested_tra"),
    THREE_DS_EXEMPTION_REQUESTED_LOW_VALUE("three_ds_exemption_requested_low_value"),
    ISSUER_THREE_DS_EXEMPTION_REQUESTED("issuer_three_ds_exemption_requested");

    private final String value;

    ThreeDSDecision(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ThreeDSDecision fromString(String value) {
        for (ThreeDSDecision decision : ThreeDSDecision.values()) {
            if (decision.value.equalsIgnoreCase(value)) {
                return decision;
            }
        }
        return NO_THREE_DS; // Default
    }
}


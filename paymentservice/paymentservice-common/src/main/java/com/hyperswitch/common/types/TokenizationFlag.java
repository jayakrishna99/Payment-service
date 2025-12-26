package com.hyperswitch.common.types;

/**
 * Enum for tokenization flag status
 */
public enum TokenizationFlag {
    ENABLED("enabled"),
    DISABLED("disabled");

    private final String value;

    TokenizationFlag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TokenizationFlag fromString(String value) {
        for (TokenizationFlag flag : TokenizationFlag.values()) {
            if (flag.value.equalsIgnoreCase(value)) {
                return flag;
            }
        }
        return ENABLED; // Default
    }
}


package com.hyperswitch.common.errors;

import java.util.Objects;

/**
 * Payment processing error
 */
public final class PaymentError {
    private final String code;
    private final String message;
    private final String connectorError;
    
    public PaymentError(String code, String message, String connectorError) {
        this.code = code;
        this.message = message;
        this.connectorError = connectorError;
    }
    
    public static PaymentError of(String code, String message) {
        return new PaymentError(code, message, null);
    }
    
    public static PaymentError connectorError(String code, String message, String connectorError) {
        return new PaymentError(code, message, connectorError);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getConnectorError() {
        return connectorError;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentError that = (PaymentError) o;
        return Objects.equals(code, that.code) && 
               Objects.equals(message, that.message) && 
               Objects.equals(connectorError, that.connectorError);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, connectorError);
    }
}


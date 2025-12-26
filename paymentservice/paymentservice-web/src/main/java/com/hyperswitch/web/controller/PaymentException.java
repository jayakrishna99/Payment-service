package com.hyperswitch.web.controller;

import com.hyperswitch.common.errors.PaymentError;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception for payment processing errors
 */
public class PaymentException extends ResponseStatusException {
    
    private final PaymentError error;

    public PaymentException(PaymentError error) {
        super(HttpStatus.BAD_REQUEST, error.getMessage());
        this.error = error;
    }

    public PaymentError getError() {
        return error;
    }
}


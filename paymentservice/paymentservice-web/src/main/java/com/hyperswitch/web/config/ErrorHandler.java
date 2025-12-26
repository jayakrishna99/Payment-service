package com.hyperswitch.web.config;

import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.web.controller.PaymentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Global exception handler
 */
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(PaymentException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handlePaymentException(PaymentException ex) {
        PaymentError error = ex.getError();
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of(
                "error", Map.of(
                    "code", error.getCode(),
                    "message", error.getMessage()
                )
            )));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, String>>> handleGenericException(Exception ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error", "Internal server error: " + ex.getMessage())));
    }
}


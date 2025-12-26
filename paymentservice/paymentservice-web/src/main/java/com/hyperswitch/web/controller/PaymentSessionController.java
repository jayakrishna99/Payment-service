package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.CreatePaymentSessionRequest;
import com.hyperswitch.common.dto.PaymentSessionResponse;
import com.hyperswitch.core.paymentsessions.PaymentSessionService;
import com.hyperswitch.web.controller.PaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * REST controller for payment sessions (v2 API)
 */
@RestController
@RequestMapping("/api/v2/payment-sessions")
public class PaymentSessionController {

    private final PaymentSessionService sessionService;

    @Autowired
    public PaymentSessionController(PaymentSessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * Create a payment session
     * POST /api/v2/payment-sessions
     */
    @PostMapping
    public Mono<ResponseEntity<PaymentSessionResponse>> createSession(
            @RequestBody CreatePaymentSessionRequest request) {
        return sessionService.createSession(request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Get a payment session by session ID
     * GET /api/v2/payment-sessions/{sessionId}
     */
    @GetMapping("/{sessionId}")
    public Mono<ResponseEntity<PaymentSessionResponse>> getSession(
            @PathVariable String sessionId,
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return sessionService.getSession(sessionId, merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Get a payment session by session token
     * GET /api/v2/payment-sessions/token/{sessionToken}
     */
    @GetMapping("/token/{sessionToken}")
    public Mono<ResponseEntity<PaymentSessionResponse>> getSessionByToken(
            @PathVariable String sessionToken) {
        return sessionService.getSessionByToken(sessionToken)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Complete a payment session
     * POST /api/v2/payment-sessions/{sessionId}/complete
     */
    @PostMapping("/{sessionId}/complete")
    public Mono<ResponseEntity<PaymentSessionResponse>> completeSession(
            @PathVariable String sessionId,
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam String paymentId) {
        return sessionService.completeSession(sessionId, paymentId, merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Cancel a payment session
     * POST /api/v2/payment-sessions/{sessionId}/cancel
     */
    @PostMapping("/{sessionId}/cancel")
    public Mono<ResponseEntity<PaymentSessionResponse>> cancelSession(
            @PathVariable String sessionId,
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return sessionService.cancelSession(sessionId, merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}


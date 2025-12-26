package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.FraudCheckResponse;
import com.hyperswitch.common.types.FraudCheckStatus;
import com.hyperswitch.web.controller.PaymentException;
import com.hyperswitch.common.types.FraudCheckType;
import com.hyperswitch.core.fraudcheck.FraudCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for fraud check management
 */
@RestController
@RequestMapping("/api/fraud_checks")
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @Autowired
    public FraudCheckController(FraudCheckService fraudCheckService) {
        this.fraudCheckService = fraudCheckService;
    }

    /**
     * Perform a fraud check
     */
    @PostMapping
    public Mono<ResponseEntity<FraudCheckResponse>> performFraudCheck(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam String paymentId,
            @RequestParam String attemptId,
            @RequestParam String frmName,
            @RequestParam(defaultValue = "PRE_FRM") FraudCheckType fraudCheckType) {
        return fraudCheckService.performFraudCheck(merchantId, paymentId, attemptId, frmName, fraudCheckType)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Get a fraud check by ID
     */
    @GetMapping("/{frmId}")
    public Mono<ResponseEntity<FraudCheckResponse>> getFraudCheck(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String frmId) {
        return fraudCheckService.getFraudCheck(merchantId, frmId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * List fraud checks for a payment
     */
    @GetMapping("/payment/{paymentId}")
    public Mono<ResponseEntity<Flux<FraudCheckResponse>>> getFraudChecksByPayment(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String paymentId) {
        Flux<FraudCheckResponse> fraudChecks = fraudCheckService.getFraudChecksByPayment(merchantId, paymentId);
        return Mono.just(ResponseEntity.ok(fraudChecks));
    }

    /**
     * Update fraud check status
     */
    @PutMapping("/{frmId}/status")
    public Mono<ResponseEntity<FraudCheckResponse>> updateFraudCheckStatus(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String frmId,
            @RequestParam FraudCheckStatus status,
            @RequestParam(required = false) Integer score,
            @RequestParam(required = false) String reason) {
        return fraudCheckService.updateFraudCheckStatus(merchantId, frmId, status, score, reason)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
}


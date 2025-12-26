package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.RevenueRecoveryAnalytics;
import com.hyperswitch.common.dto.RevenueRecoveryResponse;
import com.hyperswitch.web.controller.PaymentException;
import com.hyperswitch.common.types.RecoveryStatus;
import com.hyperswitch.common.types.RevenueRecoveryAlgorithmType;
import com.hyperswitch.core.revenuerecovery.RevenueRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for revenue recovery management
 */
@RestController
@RequestMapping("/api/revenue_recovery")
public class RevenueRecoveryController {

    private final RevenueRecoveryService revenueRecoveryService;

    @Autowired
    public RevenueRecoveryController(RevenueRecoveryService revenueRecoveryService) {
        this.revenueRecoveryService = revenueRecoveryService;
    }

    /**
     * Create or update a revenue recovery record
     */
    @PostMapping
    public Mono<ResponseEntity<RevenueRecoveryResponse>> createOrUpdateRecovery(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam String paymentId,
            @RequestParam String attemptId,
            @RequestParam String profileId,
            @RequestParam String billingMcaId,
            @RequestParam(required = false, defaultValue = "EXPONENTIAL_BACKOFF") RevenueRecoveryAlgorithmType algorithmType,
            @RequestParam(required = false) Long retryBudget) {
        return revenueRecoveryService.createOrUpdateRecovery(
            merchantId, paymentId, attemptId, profileId, billingMcaId, algorithmType, retryBudget)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Get revenue recovery by ID
     */
    @GetMapping("/{recoveryId}")
    public Mono<ResponseEntity<RevenueRecoveryResponse>> getRecovery(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String recoveryId) {
        return revenueRecoveryService.getRecovery(merchantId, recoveryId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Get revenue recovery by payment and attempt
     */
    @GetMapping("/payment/{paymentId}/attempt/{attemptId}")
    public Mono<ResponseEntity<RevenueRecoveryResponse>> getRecoveryByPayment(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String paymentId,
            @PathVariable String attemptId) {
        return revenueRecoveryService.getRecoveryByPayment(merchantId, paymentId, attemptId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * List revenue recoveries
     */
    @GetMapping
    public Mono<ResponseEntity<Flux<RevenueRecoveryResponse>>> listRecoveries(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false) RecoveryStatus status) {
        Flux<RevenueRecoveryResponse> recoveries = revenueRecoveryService.listRecoveries(merchantId, status);
        return Mono.just(ResponseEntity.ok(recoveries));
    }

    /**
     * Update recovery status
     */
    @PutMapping("/{recoveryId}/status")
    public Mono<ResponseEntity<RevenueRecoveryResponse>> updateRecoveryStatus(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String recoveryId,
            @RequestParam RecoveryStatus status) {
        return revenueRecoveryService.updateRecoveryStatus(merchantId, recoveryId, status)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Calculate next retry delay
     */
    @GetMapping("/calculate-delay")
    public Mono<ResponseEntity<Long>> calculateNextRetryDelay(
            @RequestParam RevenueRecoveryAlgorithmType algorithmType,
            @RequestParam(required = false, defaultValue = "0") Integer retryCount,
            @RequestParam(required = false, defaultValue = "60") Long baseDelaySeconds) {
        return revenueRecoveryService.calculateNextRetryDelay(algorithmType, retryCount, baseDelaySeconds)
            .map(ResponseEntity::ok);
    }

    /**
     * Get revenue recovery analytics
     */
    @GetMapping("/analytics")
    public Mono<ResponseEntity<RevenueRecoveryAnalytics>> getAnalytics(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return revenueRecoveryService.getAnalytics(merchantId)
            .map(ResponseEntity::ok);
    }
}


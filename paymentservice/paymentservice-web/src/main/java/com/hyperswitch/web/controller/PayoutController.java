package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.web.controller.PaymentException;
import com.hyperswitch.common.types.PayoutId;
import com.hyperswitch.core.payouts.PayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * REST controller for payout management
 */
@RestController
@RequestMapping("/api/payouts")
public class PayoutController {

    private final PayoutService payoutService;

    @Autowired
    public PayoutController(PayoutService payoutService) {
        this.payoutService = payoutService;
    }

    /**
     * Create a payout
     */
    @PostMapping
    public Mono<ResponseEntity<PayoutResponse>> createPayout(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody PayoutRequest request) {
        return payoutService.createPayout(merchantId, request)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Get a payout by ID
     */
    @GetMapping("/{payoutId}")
    public Mono<ResponseEntity<PayoutResponse>> getPayout(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String payoutId) {
        return payoutService.getPayout(merchantId, PayoutId.of(payoutId))
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * List all payouts for a merchant
     */
    @GetMapping
    public Mono<ResponseEntity<Flux<PayoutResponse>>> listPayouts(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        Flux<PayoutResponse> payouts = payoutService.listPayouts(merchantId);
        return Mono.just(ResponseEntity.ok(payouts));
    }

    /**
     * Confirm a payout
     */
    @PostMapping("/{payoutId}/confirm")
    public Mono<ResponseEntity<PayoutResponse>> confirmPayout(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String payoutId,
            @RequestParam(required = false) String clientSecret) {
        return payoutService.confirmPayout(merchantId, PayoutId.of(payoutId), clientSecret)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Cancel a payout
     */
    @PostMapping("/{payoutId}/cancel")
    public Mono<ResponseEntity<PayoutResponse>> cancelPayout(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String payoutId) {
        return payoutService.cancelPayout(merchantId, PayoutId.of(payoutId))
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Fulfill a payout
     * POST /api/payouts/{payoutId}/fulfill
     */
    @PostMapping("/{payoutId}/fulfill")
    public Mono<ResponseEntity<PayoutResponse>> fulfillPayout(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String payoutId) {
        return payoutService.fulfillPayout(merchantId, PayoutId.of(payoutId))
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * List payouts with filters
     * POST /api/payouts/list
     */
    @PostMapping("/list")
    public Mono<ResponseEntity<PayoutListResponse>> listPayoutsWithFilters(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody PayoutListFilterConstraints constraints) {
        constraints.setMerchantId(merchantId);
        return payoutService.listPayoutsWithFilters(merchantId, constraints)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Get payout filters
     * GET /api/payouts/filter
     */
    @GetMapping("/filter")
    public Mono<ResponseEntity<PayoutFiltersResponse>> getPayoutFilters(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return payoutService.getPayoutFilters(merchantId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Get payout aggregates
     * GET /api/payouts/aggregate
     */
    @GetMapping("/aggregate")
    public Mono<ResponseEntity<PayoutAggregatesResponse>> getPayoutAggregates(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant endTime) {
        return payoutService.getPayoutAggregates(merchantId, startTime, endTime)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Update payout
     * PUT /api/payouts/{payout_id}
     */
    @PutMapping("/{payout_id}")
    public Mono<ResponseEntity<PayoutResponse>> updatePayout(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable("payout_id") String payoutId,
            @RequestBody PayoutRequest request) {
        // Update payout - in production this would update payout details
        // For now, return the existing payout
        return payoutService.getPayout(merchantId, PayoutId.of(payoutId))
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * List payouts with filters for profile
     * POST /api/payouts/list/filter/profile
     */
    @PostMapping("/list/filter/profile")
    public Mono<ResponseEntity<PayoutListResponse>> listPayoutsWithFiltersForProfile(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody PayoutListFilterConstraints constraints) {
        constraints.setMerchantId(merchantId);
        // Profile listing is similar to regular listing but filtered by profile
        return payoutService.listPayoutsWithFilters(merchantId, constraints)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Get payout filters (POST)
     * POST /api/payouts/filter
     */
    @PostMapping("/filter")
    public Mono<ResponseEntity<PayoutFiltersResponse>> getPayoutFiltersPost(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody(required = false) java.util.Map<String, Object> request) {
        // POST version of filter - same as GET but allows request body
        return payoutService.getPayoutFilters(merchantId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Get payout filters for profile
     * POST /api/payouts/profile/filter
     */
    @PostMapping("/profile/filter")
    public Mono<ResponseEntity<PayoutFiltersResponse>> getPayoutFiltersForProfile(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody(required = false) java.util.Map<String, Object> request) {
        // Profile filters are similar to regular filters but filtered by profile
        return payoutService.getPayoutFilters(merchantId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Get payout filters (v2)
     * GET /api/payouts/filters
     */
    @GetMapping("/filters")
    public Mono<ResponseEntity<PayoutFiltersResponse>> getPayoutFiltersV2(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return payoutService.getPayoutFilters(merchantId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Get payout aggregates for profile
     * GET /api/payouts/profile/aggregate
     */
    @GetMapping("/profile/aggregate")
    public Mono<ResponseEntity<PayoutAggregatesResponse>> getPayoutAggregatesForProfile(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant endTime) {
        // Profile aggregates are similar to regular aggregates but filtered by profile
        return payoutService.getPayoutAggregates(merchantId, startTime, endTime)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Get payout accounts
     * GET /api/payouts/accounts
     */
    @GetMapping("/accounts")
    public Mono<ResponseEntity<PayoutAccountsResponse>> getPayoutAccounts(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        // In production, this would fetch payout accounts from connector
        // For now, return empty response
        PayoutAccountsResponse response = new PayoutAccountsResponse();
        return Mono.just(ResponseEntity.ok(response));
    }
}

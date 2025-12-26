package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.SubscriptionRequest;
import com.hyperswitch.common.dto.SubscriptionResponse;
import com.hyperswitch.common.dto.SubscriptionItemsResponse;
import com.hyperswitch.common.dto.SubscriptionEstimateResponse;
import com.hyperswitch.web.controller.PaymentException;
import com.hyperswitch.common.types.SubscriptionId;
import com.hyperswitch.core.subscriptions.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for subscription management
 */
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    /**
     * Create a subscription
     */
    @PostMapping
    public Mono<ResponseEntity<SubscriptionResponse>> createSubscription(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody SubscriptionRequest request) {
        return subscriptionService.createSubscription(merchantId, request)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Get a subscription by ID
     */
    @GetMapping("/{subscriptionId}")
    public Mono<ResponseEntity<SubscriptionResponse>> getSubscription(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String subscriptionId) {
        return subscriptionService.getSubscription(merchantId, SubscriptionId.of(subscriptionId))
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * List all subscriptions for a merchant
     */
    @GetMapping
    public Mono<ResponseEntity<Flux<SubscriptionResponse>>> listSubscriptions(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        Flux<SubscriptionResponse> subscriptions = subscriptionService.listSubscriptions(merchantId);
        return Mono.just(ResponseEntity.ok(subscriptions));
    }

    /**
     * List subscriptions for a customer
     */
    @GetMapping("/customer/{customerId}")
    public Mono<ResponseEntity<Flux<SubscriptionResponse>>> listSubscriptionsByCustomer(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String customerId) {
        Flux<SubscriptionResponse> subscriptions = subscriptionService.listSubscriptionsByCustomer(merchantId, customerId);
        return Mono.just(ResponseEntity.ok(subscriptions));
    }

    /**
     * Update a subscription
     */
    @PutMapping("/{subscriptionId}")
    public Mono<ResponseEntity<SubscriptionResponse>> updateSubscription(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String subscriptionId,
            @RequestBody SubscriptionRequest request) {
        return subscriptionService.updateSubscription(merchantId, SubscriptionId.of(subscriptionId), request)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Cancel a subscription
     */
    @PostMapping("/{subscriptionId}/cancel")
    public Mono<ResponseEntity<SubscriptionResponse>> cancelSubscription(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String subscriptionId) {
        return subscriptionService.cancelSubscription(merchantId, SubscriptionId.of(subscriptionId))
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Activate a subscription
     */
    @PostMapping("/{subscriptionId}/activate")
    public Mono<ResponseEntity<SubscriptionResponse>> activateSubscription(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String subscriptionId) {
        return subscriptionService.activateSubscription(merchantId, SubscriptionId.of(subscriptionId))
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Pause a subscription
     * POST /api/subscriptions/{subscriptionId}/pause
     */
    @PostMapping("/{subscriptionId}/pause")
    public Mono<ResponseEntity<SubscriptionResponse>> pauseSubscription(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String subscriptionId) {
        return subscriptionService.pauseSubscription(merchantId, SubscriptionId.of(subscriptionId))
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Resume a paused subscription
     * POST /api/subscriptions/{subscriptionId}/resume
     */
    @PostMapping("/{subscriptionId}/resume")
    public Mono<ResponseEntity<SubscriptionResponse>> resumeSubscription(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String subscriptionId) {
        return subscriptionService.resumeSubscription(merchantId, SubscriptionId.of(subscriptionId))
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Confirm a subscription
     * POST /api/subscriptions/{subscriptionId}/confirm
     */
    @PostMapping("/{subscriptionId}/confirm")
    public Mono<ResponseEntity<SubscriptionResponse>> confirmSubscription(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String subscriptionId) {
        return subscriptionService.confirmSubscription(merchantId, SubscriptionId.of(subscriptionId))
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Create and confirm a subscription in one call
     * POST /api/subscriptions/create_and_confirm
     */
    @PostMapping("/create_and_confirm")
    public Mono<ResponseEntity<SubscriptionResponse>> createAndConfirmSubscription(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody SubscriptionRequest request) {
        return subscriptionService.createAndConfirmSubscription(merchantId, request)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Get subscription items
     * GET /api/subscriptions/items
     */
    @GetMapping("/items")
    public Mono<ResponseEntity<Flux<SubscriptionItemsResponse>>> getSubscriptionItems(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false, defaultValue = "plan") String itemType,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset) {
        return subscriptionService.getSubscriptionItems(merchantId, itemType, limit, offset)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Get subscription estimate
     * GET /api/subscriptions/estimate
     */
    @GetMapping("/estimate")
    public Mono<ResponseEntity<SubscriptionEstimateResponse>> getSubscriptionEstimate(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam String itemPriceId,
            @RequestParam(required = false) String planId,
            @RequestParam(required = false) String couponCode) {
        return subscriptionService.getSubscriptionEstimate(merchantId, itemPriceId, planId, couponCode)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
}


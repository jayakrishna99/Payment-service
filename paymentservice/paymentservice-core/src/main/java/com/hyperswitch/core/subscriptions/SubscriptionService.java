package com.hyperswitch.core.subscriptions;

import com.hyperswitch.common.dto.SubscriptionRequest;
import com.hyperswitch.common.dto.SubscriptionResponse;
import com.hyperswitch.common.dto.SubscriptionItemsResponse;
import com.hyperswitch.common.dto.SubscriptionEstimateResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.SubscriptionId;
import io.vavr.control.Either;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for subscription management
 */
public interface SubscriptionService {

    /**
     * Create a subscription
     */
    Mono<Either<PaymentError, SubscriptionResponse>> createSubscription(String merchantId, SubscriptionRequest request);

    /**
     * Retrieve a subscription by ID
     */
    Mono<Either<PaymentError, SubscriptionResponse>> getSubscription(String merchantId, SubscriptionId subscriptionId);

    /**
     * List subscriptions for a merchant
     */
    Flux<SubscriptionResponse> listSubscriptions(String merchantId);

    /**
     * List subscriptions for a customer
     */
    Flux<SubscriptionResponse> listSubscriptionsByCustomer(String merchantId, String customerId);

    /**
     * Update a subscription
     */
    Mono<Either<PaymentError, SubscriptionResponse>> updateSubscription(String merchantId, SubscriptionId subscriptionId, SubscriptionRequest request);

    /**
     * Cancel a subscription
     */
    Mono<Either<PaymentError, SubscriptionResponse>> cancelSubscription(String merchantId, SubscriptionId subscriptionId);

    /**
     * Activate a subscription
     */
    Mono<Either<PaymentError, SubscriptionResponse>> activateSubscription(String merchantId, SubscriptionId subscriptionId);
    
    /**
     * Process billing cycle for a subscription
     * This creates a payment for the subscription's billing cycle
     */
    Mono<Either<PaymentError, SubscriptionResponse>> processBillingCycle(String merchantId, SubscriptionId subscriptionId);
    
    /**
     * Schedule recurring payment for a subscription
     * This sets up automatic billing for the subscription
     */
    Mono<Either<PaymentError, String>> scheduleRecurringPayment(String merchantId, SubscriptionId subscriptionId, String cronExpression);
    
    /**
     * Execute scheduled billing (called by scheduler)
     * Processes all subscriptions that are due for billing
     */
    Mono<Void> executeScheduledBilling(String merchantId);
    
    /**
     * Pause a subscription
     */
    Mono<Either<PaymentError, SubscriptionResponse>> pauseSubscription(String merchantId, SubscriptionId subscriptionId);
    
    /**
     * Resume a paused subscription
     */
    Mono<Either<PaymentError, SubscriptionResponse>> resumeSubscription(String merchantId, SubscriptionId subscriptionId);
    
    /**
     * Confirm a subscription
     */
    Mono<Either<PaymentError, SubscriptionResponse>> confirmSubscription(String merchantId, SubscriptionId subscriptionId);
    
    /**
     * Create and confirm a subscription in one call
     */
    Mono<Either<PaymentError, SubscriptionResponse>> createAndConfirmSubscription(String merchantId, SubscriptionRequest request);
    
    /**
     * Get subscription items
     */
    Mono<Either<PaymentError, Flux<SubscriptionItemsResponse>>> getSubscriptionItems(
        String merchantId,
        String itemType,
        Integer limit,
        Integer offset
    );
    
    /**
     * Get subscription estimate
     */
    Mono<Either<PaymentError, SubscriptionEstimateResponse>> getSubscriptionEstimate(
        String merchantId,
        String itemPriceId,
        String planId,
        String couponCode
    );
}


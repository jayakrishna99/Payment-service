package com.hyperswitch.core.payouts;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.PayoutId;
import io.vavr.control.Either;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * Service interface for payout management
 */
public interface PayoutService {

    /**
     * Create a payout
     */
    Mono<Either<PaymentError, PayoutResponse>> createPayout(String merchantId, PayoutRequest request);

    /**
     * Retrieve a payout by ID
     */
    Mono<Either<PaymentError, PayoutResponse>> getPayout(String merchantId, PayoutId payoutId);

    /**
     * List payouts for a merchant
     */
    Flux<PayoutResponse> listPayouts(String merchantId);

    /**
     * Confirm a payout
     */
    Mono<Either<PaymentError, PayoutResponse>> confirmPayout(String merchantId, PayoutId payoutId, String clientSecret);

    /**
     * Cancel a payout
     */
    Mono<Either<PaymentError, PayoutResponse>> cancelPayout(String merchantId, PayoutId payoutId);
    
    /**
     * Fulfill a payout
     */
    Mono<Either<PaymentError, PayoutResponse>> fulfillPayout(String merchantId, PayoutId payoutId);
    
    /**
     * List payouts with filters
     */
    Mono<Either<PaymentError, PayoutListResponse>> listPayoutsWithFilters(
        String merchantId,
        PayoutListFilterConstraints constraints
    );
    
    /**
     * Get available filter options for payouts
     */
    Mono<Either<PaymentError, PayoutFiltersResponse>> getPayoutFilters(String merchantId);
    
    /**
     * Get payout aggregates (status counts)
     */
    Mono<Either<PaymentError, PayoutAggregatesResponse>> getPayoutAggregates(
        String merchantId,
        Instant startTime,
        Instant endTime
    );
}


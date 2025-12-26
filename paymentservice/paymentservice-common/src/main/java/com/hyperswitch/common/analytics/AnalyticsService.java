package com.hyperswitch.common.analytics;

import com.hyperswitch.common.dto.ConnectorAnalyticsResponse;
import com.hyperswitch.common.dto.CustomerAnalyticsResponse;
import com.hyperswitch.common.dto.PaymentAnalyticsResponse;
import com.hyperswitch.common.dto.RevenueAnalyticsResponse;
import com.hyperswitch.common.enums.Connector;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Analytics service for tracking payment success rates and performance metrics
 */
public interface AnalyticsService {
    
    /**
     * Record a payment attempt result for analytics
     */
    Mono<Void> recordPaymentAttempt(
        String merchantId,
        String profileId,
        Connector connector,
        String paymentMethod,
        String currency,
        boolean success
    );
    
    /**
     * Get success rate for a connector
     */
    Mono<BigDecimal> getSuccessRate(
        String merchantId,
        String profileId,
        Connector connector,
        String paymentMethod,
        String currency
    );
    
    /**
     * Recalculate success rates for all connectors
     */
    Mono<Void> recalculateSuccessRates(String merchantId, String profileId);
    
    /**
     * Get payment analytics for a merchant
     */
    Mono<PaymentAnalyticsResponse> getPaymentAnalytics(
        String merchantId,
        Instant startDate,
        Instant endDate,
        String currency
    );
    
    /**
     * Get connector analytics
     */
    Flux<ConnectorAnalyticsResponse> getConnectorAnalytics(
        String merchantId,
        Instant startDate,
        Instant endDate
    );
    
    /**
     * Get revenue analytics for a merchant
     */
    Mono<RevenueAnalyticsResponse> getRevenueAnalytics(
        String merchantId,
        Instant startDate,
        Instant endDate
    );
    
    /**
     * Get customer analytics
     */
    Mono<CustomerAnalyticsResponse> getCustomerAnalytics(
        String merchantId,
        String customerId
    );
    
    /**
     * Update success rate window with a new payment attempt result
     * @param profileId Profile ID
     * @param connector Connector name
     * @param paymentMethod Payment method
     * @param currency Currency
     * @param success Whether the payment succeeded
     * @param windowDurationMinutes Window duration in minutes (e.g., 60 for 1 hour)
     */
    Mono<Void> updateSuccessRateWindow(
        String profileId,
        String connector,
        String paymentMethod,
        String currency,
        boolean success,
        int windowDurationMinutes
    );
    
    /**
     * Get aggregated success rate for a time window
     * @param profileId Profile ID
     * @param connector Connector name
     * @param paymentMethod Payment method
     * @param currency Currency
     * @param windowDurationMinutes Window duration in minutes
     * @return Aggregated success rate
     */
    Mono<BigDecimal> getWindowedSuccessRate(
        String profileId,
        String connector,
        String paymentMethod,
        String currency,
        int windowDurationMinutes
    );
}


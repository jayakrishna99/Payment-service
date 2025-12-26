package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.ConnectorAnalyticsResponse;
import com.hyperswitch.common.dto.CustomerAnalyticsResponse;
import com.hyperswitch.common.dto.PaymentAnalyticsResponse;
import com.hyperswitch.common.dto.RevenueAnalyticsResponse;
import com.hyperswitch.common.analytics.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * REST controller for analytics
 */
@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    /**
     * Get payment analytics
     */
    @GetMapping("/payments")
    public Mono<ResponseEntity<PaymentAnalyticsResponse>> getPaymentAnalytics(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant endDate,
            @RequestParam(required = false) String currency) {
        return analyticsService.getPaymentAnalytics(merchantId, startDate, endDate, currency)
            .map(ResponseEntity::ok);
    }

    /**
     * Get connector analytics
     */
    @GetMapping("/connectors")
    public Mono<ResponseEntity<Flux<ConnectorAnalyticsResponse>>> getConnectorAnalytics(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant endDate) {
        Flux<ConnectorAnalyticsResponse> analytics = analyticsService.getConnectorAnalytics(merchantId, startDate, endDate);
        return Mono.just(ResponseEntity.ok(analytics));
    }

    /**
     * Get revenue analytics
     */
    @GetMapping("/revenue")
    public Mono<ResponseEntity<RevenueAnalyticsResponse>> getRevenueAnalytics(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant endDate) {
        return analyticsService.getRevenueAnalytics(merchantId, startDate, endDate)
            .map(ResponseEntity::ok);
    }

    /**
     * Get customer analytics
     */
    @GetMapping("/customers/{customerId}")
    public Mono<ResponseEntity<CustomerAnalyticsResponse>> getCustomerAnalytics(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String customerId) {
        return analyticsService.getCustomerAnalytics(merchantId, customerId)
            .map(ResponseEntity::ok);
    }
}


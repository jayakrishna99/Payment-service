package com.hyperswitch.core.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Custom business metrics for payment operations
 */
@Component
public class PaymentMetrics {

    private static final String METRIC_PREFIX = "hyperswitch.payment";
    private static final String TAG_STATUS = "status";

    private final Counter paymentCreatedCounter;
    private final Counter paymentConfirmedCounter;
    private final Counter paymentCapturedCounter;
    private final Counter paymentRefundedCounter;
    private final Counter paymentFailedCounter;
    private final Counter payment3DSInitiatedCounter;
    private final Counter payment3DSCompletedCounter;
    
    private final Timer paymentProcessingTimer;
    private final Timer paymentConfirmationTimer;
    private final Timer paymentCaptureTimer;
    private final Timer refundProcessingTimer;
    
    private final Counter mandateCreatedCounter;
    private final Counter mandateRevokedCounter;
    private final Counter paymentLinkCreatedCounter;

    public PaymentMetrics(MeterRegistry meterRegistry) {
        // Payment counters
        this.paymentCreatedCounter = Counter.builder(METRIC_PREFIX + ".created")
            .description("Total number of payments created")
            .register(meterRegistry);
        
        this.paymentConfirmedCounter = Counter.builder(METRIC_PREFIX + ".confirmed")
            .description("Total number of payments confirmed")
            .tag(TAG_STATUS, "success")
            .register(meterRegistry);
        
        this.paymentCapturedCounter = Counter.builder(METRIC_PREFIX + ".captured")
            .description("Total number of payments captured")
            .register(meterRegistry);
        
        this.paymentRefundedCounter = Counter.builder(METRIC_PREFIX + ".refunded")
            .description("Total number of payments refunded")
            .register(meterRegistry);
        
        this.paymentFailedCounter = Counter.builder(METRIC_PREFIX + ".failed")
            .description("Total number of failed payments")
            .tag(TAG_STATUS, "failed")
            .register(meterRegistry);
        
        this.payment3DSInitiatedCounter = Counter.builder(METRIC_PREFIX + ".3ds.initiated")
            .description("Total number of 3DS authentications initiated")
            .register(meterRegistry);
        
        this.payment3DSCompletedCounter = Counter.builder(METRIC_PREFIX + ".3ds.completed")
            .description("Total number of 3DS authentications completed")
            .register(meterRegistry);
        
        // Payment timers
        this.paymentProcessingTimer = Timer.builder(METRIC_PREFIX + ".processing.duration")
            .description("Time taken to process payment")
            .register(meterRegistry);
        
        this.paymentConfirmationTimer = Timer.builder(METRIC_PREFIX + ".confirmation.duration")
            .description("Time taken to confirm payment")
            .register(meterRegistry);
        
        this.paymentCaptureTimer = Timer.builder(METRIC_PREFIX + ".capture.duration")
            .description("Time taken to capture payment")
            .register(meterRegistry);
        
        this.refundProcessingTimer = Timer.builder(METRIC_PREFIX + ".refund.duration")
            .description("Time taken to process refund")
            .register(meterRegistry);
        
        // Mandate counters
        this.mandateCreatedCounter = Counter.builder("hyperswitch.mandate.created")
            .description("Total number of mandates created")
            .register(meterRegistry);
        
        this.mandateRevokedCounter = Counter.builder("hyperswitch.mandate.revoked")
            .description("Total number of mandates revoked")
            .register(meterRegistry);
        
        // Payment link counter
        this.paymentLinkCreatedCounter = Counter.builder("hyperswitch.payment_link.created")
            .description("Total number of payment links created")
            .register(meterRegistry);
    }

    public void incrementPaymentCreated() {
        paymentCreatedCounter.increment();
    }

    public void incrementPaymentConfirmed(String connector, String currency) {
        paymentConfirmedCounter.increment();
    }

    public void incrementPaymentCaptured() {
        paymentCapturedCounter.increment();
    }

    public void incrementPaymentRefunded() {
        paymentRefundedCounter.increment();
    }

    public void incrementPaymentFailed(String connector, String errorCode) {
        paymentFailedCounter.increment();
    }

    public void increment3DSInitiated() {
        payment3DSInitiatedCounter.increment();
    }

    public void increment3DSCompleted() {
        payment3DSCompletedCounter.increment();
    }

    public void recordPaymentProcessingTime(long duration, TimeUnit unit) {
        paymentProcessingTimer.record(duration, unit);
    }

    public void recordPaymentConfirmationTime(long duration, TimeUnit unit) {
        paymentConfirmationTimer.record(duration, unit);
    }

    public void recordPaymentCaptureTime(long duration, TimeUnit unit) {
        paymentCaptureTimer.record(duration, unit);
    }

    public void recordRefundProcessingTime(long duration, TimeUnit unit) {
        refundProcessingTimer.record(duration, unit);
    }

    public void incrementMandateCreated() {
        mandateCreatedCounter.increment();
    }

    public void incrementMandateRevoked() {
        mandateRevokedCounter.increment();
    }

    public void incrementPaymentLinkCreated() {
        paymentLinkCreatedCounter.increment();
    }
}


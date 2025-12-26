package com.hyperswitch.core.reconciliation;

import com.hyperswitch.common.dto.ReconciliationRequest;
import com.hyperswitch.common.dto.ReconciliationResponse;
import com.hyperswitch.common.dto.ReconStatusResponse;
import com.hyperswitch.common.dto.ReconTokenResponse;
import com.hyperswitch.common.errors.PaymentError;
import io.vavr.control.Either;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for reconciliation management
 */
public interface ReconciliationService {

    /**
     * Start a reconciliation run
     */
    Mono<Either<PaymentError, ReconciliationResponse>> startReconciliation(
        String merchantId,
        ReconciliationRequest request
    );

    /**
     * Get reconciliation by ID
     */
    Mono<Either<PaymentError, ReconciliationResponse>> getReconciliation(
        String merchantId,
        String reconciliationId
    );

    /**
     * List reconciliations for a merchant
     */
    Flux<ReconciliationResponse> listReconciliations(String merchantId);

    /**
     * Get reconciliation status for a merchant
     */
    Mono<ReconStatusResponse> getReconciliationStatus(String merchantId);

    /**
     * Request reconciliation activation
     */
    Mono<Either<PaymentError, ReconStatusResponse>> requestReconciliation(String merchantId);

    /**
     * Generate reconciliation token
     */
    Mono<Either<PaymentError, ReconTokenResponse>> generateReconciliationToken(String merchantId);
    
    /**
     * Schedule automatic reconciliation for a merchant
     * @param merchantId Merchant ID
     * @param scheduleCronExpression Cron expression for scheduling (e.g., "0 0 2 * * ?" for daily at 2 AM)
     * @return Success or error
     */
    Mono<Either<PaymentError, String>> scheduleReconciliation(String merchantId, String scheduleCronExpression);
    
    /**
     * Cancel scheduled reconciliation for a merchant
     * @param merchantId Merchant ID
     * @return Success or error
     */
    Mono<Either<PaymentError, Void>> cancelScheduledReconciliation(String merchantId);
    
    /**
     * Execute scheduled reconciliation (called by scheduler)
     * @param merchantId Merchant ID
     * @return Reconciliation response
     */
    Mono<Either<PaymentError, ReconciliationResponse>> executeScheduledReconciliation(String merchantId);
    
    /**
     * Perform 2-way reconciliation - Compare internal records with connector records
     * @param merchantId Merchant ID
     * @param reconciliationId Reconciliation ID
     * @return Reconciliation response with match results
     */
    Mono<Either<PaymentError, ReconciliationResponse>> performTwoWayReconciliation(
        String merchantId,
        String reconciliationId
    );
    
    /**
     * Perform 3-way reconciliation - Compare internal, connector, and bank records
     * @param merchantId Merchant ID
     * @param reconciliationId Reconciliation ID
     * @param bankRecords Bank statement records (CSV or JSON format)
     * @return Reconciliation response with match results
     */
    Mono<Either<PaymentError, ReconciliationResponse>> performThreeWayReconciliation(
        String merchantId,
        String reconciliationId,
        String bankRecords
    );
    
    /**
     * Generate detailed reconciliation report with discrepancy analysis
     * @param merchantId Merchant ID
     * @param reconciliationId Reconciliation ID
     * @return Detailed reconciliation report
     */
    Mono<Either<PaymentError, com.hyperswitch.common.dto.ReconciliationReportResponse>> generateReconciliationReport(
        String merchantId,
        String reconciliationId
    );
    
    /**
     * Export reconciliation report in specified format (CSV, PDF, JSON)
     * @param merchantId Merchant ID
     * @param reconciliationId Reconciliation ID
     * @param format Export format ("CSV", "PDF", "JSON")
     * @return Report content as byte array
     */
    Mono<Either<PaymentError, byte[]>> exportReconciliationReport(
        String merchantId,
        String reconciliationId,
        String format
    );
}


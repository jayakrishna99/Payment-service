package com.hyperswitch.core.disputes;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.DisputeId;
import io.vavr.control.Either;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * Service interface for dispute management
 */
public interface DisputeService {

    /**
     * Retrieve a dispute by ID
     */
    Mono<Either<PaymentError, DisputeResponse>> getDispute(String merchantId, DisputeId disputeId);

    /**
     * List disputes for a merchant
     */
    Flux<DisputeResponse> listDisputes(String merchantId);

    /**
     * List disputes for a payment
     */
    Flux<DisputeResponse> listDisputesByPayment(String merchantId, String paymentId);

    /**
     * Accept a dispute
     */
    Mono<Either<PaymentError, DisputeResponse>> acceptDispute(String merchantId, DisputeId disputeId);

    /**
     * Submit evidence for a dispute
     */
    Mono<Either<PaymentError, DisputeResponse>> submitEvidence(String merchantId, SubmitEvidenceRequest request);

    /**
     * Create or update dispute from webhook
     */
    Mono<Either<PaymentError, DisputeResponse>> createOrUpdateDispute(
        String merchantId,
        String paymentId,
        String attemptId,
        String connector,
        String connectorDisputeId,
        String amount,
        String currency,
        String connectorStatus,
        String disputeStage,
        String disputeStatus,
        String connectorReason,
        String connectorReasonCode
    );
    
    /**
     * Defend a dispute by submitting evidence and contesting it
     * This is a convenience method that submits evidence and marks the dispute as being defended
     */
    Mono<Either<PaymentError, DisputeResponse>> defendDispute(
        String merchantId,
        DisputeId disputeId,
        SubmitEvidenceRequest evidenceRequest
    );
    
    /**
     * Sync dispute status with connector
     * Fetches the latest dispute status from the payment processor
     */
    Mono<Either<PaymentError, DisputeResponse>> syncDispute(
        String merchantId,
        DisputeId disputeId
    );
    
    /**
     * List disputes with filters
     * Supports filtering by status, stage, connector, currency, time range, etc.
     */
    Mono<Either<PaymentError, DisputeListResponse>> listDisputesWithFilters(
        String merchantId,
        DisputeListFilterConstraints constraints
    );
    
    /**
     * Get available filter options for disputes
     * Returns lists of available connectors, currencies, statuses, stages, and reasons
     */
    Mono<Either<PaymentError, DisputeFiltersResponse>> getDisputeFilters(String merchantId);
    
    /**
     * Get dispute aggregates (status and stage counts)
     * Returns counts of disputes by status and stage within a time range
     */
    Mono<Either<PaymentError, DisputeAggregatesResponse>> getDisputeAggregates(
        String merchantId,
        Instant startTime,
        Instant endTime
    );
    
    /**
     * Attach dispute evidence
     */
    Mono<Either<PaymentError, DisputeResponse>> attachEvidence(
        String merchantId,
        SubmitEvidenceRequest request
    );
    
    /**
     * Retrieve dispute evidence
     */
    Mono<Either<PaymentError, DisputeEvidenceResponse>> retrieveEvidence(
        String merchantId,
        DisputeId disputeId
    );
    
    /**
     * Delete dispute evidence
     */
    Mono<Either<PaymentError, Void>> deleteEvidence(
        String merchantId,
        String fileId
    );
    
    /**
     * Fetch disputes from connector
     */
    Flux<DisputeResponse> fetchDisputesFromConnector(
        String merchantId,
        String connectorId
    );
}


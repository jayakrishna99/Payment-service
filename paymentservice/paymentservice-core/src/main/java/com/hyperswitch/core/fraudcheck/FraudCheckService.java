package com.hyperswitch.core.fraudcheck;

import com.hyperswitch.common.dto.FraudCheckResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.FraudCheckStatus;
import com.hyperswitch.common.types.FraudCheckType;
import io.vavr.control.Either;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for fraud check management
 */
public interface FraudCheckService {

    /**
     * Perform a fraud check for a payment
     */
    Mono<Either<PaymentError, FraudCheckResponse>> performFraudCheck(
        String merchantId,
        String paymentId,
        String attemptId,
        String frmName,
        FraudCheckType fraudCheckType
    );

    /**
     * Get fraud check by ID
     */
    Mono<Either<PaymentError, FraudCheckResponse>> getFraudCheck(String merchantId, String frmId);

    /**
     * Get fraud checks for a payment
     */
    Flux<FraudCheckResponse> getFraudChecksByPayment(String merchantId, String paymentId);

    /**
     * Update fraud check status
     */
    Mono<Either<PaymentError, FraudCheckResponse>> updateFraudCheckStatus(
        String merchantId,
        String frmId,
        FraudCheckStatus status,
        Integer score,
        String reason
    );
}


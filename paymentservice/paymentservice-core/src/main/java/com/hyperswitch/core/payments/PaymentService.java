package com.hyperswitch.core.payments;

import com.hyperswitch.common.dto.CreatePaymentRequest;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.PaymentId;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Core payment processing service
 * Handles payment creation, confirmation, capture, and refund operations
 */
public interface PaymentService {
    
    /**
     * Create a new payment intent
     */
    Mono<Result<PaymentIntent, PaymentError>> createPayment(CreatePaymentRequest request);
    
    /**
     * Confirm a payment (process the payment)
     */
    Mono<Result<PaymentIntent, PaymentError>> confirmPayment(PaymentId paymentId, ConfirmPaymentRequest request);
    
    /**
     * Capture a payment (for manual capture flows)
     */
    Mono<Result<PaymentIntent, PaymentError>> capturePayment(PaymentId paymentId, CapturePaymentRequest request);
    
    /**
     * Get payment status
     */
    Mono<Result<PaymentIntent, PaymentError>> getPayment(PaymentId paymentId);
    
    /**
     * Process refund
     */
    Mono<Result<Refund, PaymentError>> refundPayment(PaymentId paymentId, RefundRequest request);
    
    /**
     * Handle 3DS challenge
     */
    Mono<Result<com.hyperswitch.common.dto.ThreeDSResponse, PaymentError>> handle3DSChallenge(
            PaymentId paymentId, 
            com.hyperswitch.common.dto.ThreeDSRequest request);
    
    /**
     * Resume payment after 3DS authentication
     */
    Mono<Result<PaymentIntent, PaymentError>> resumePaymentAfter3DS(
            PaymentId paymentId, 
            String authenticationId);
    
    /**
     * Cancel a payment
     */
    Mono<Result<PaymentIntent, PaymentError>> cancelPayment(
            PaymentId paymentId, 
            com.hyperswitch.common.dto.CancelPaymentRequest request);
    
    /**
     * Update a payment
     */
    Mono<Result<PaymentIntent, PaymentError>> updatePayment(
            PaymentId paymentId, 
            com.hyperswitch.common.dto.UpdatePaymentRequest request);
    
    /**
     * Get client secret for a payment
     */
    Mono<Result<String, PaymentError>> getClientSecret(PaymentId paymentId);
    
    /**
     * Incremental authorization - increase authorized amount for a payment
     */
    Mono<Result<PaymentIntent, PaymentError>> incrementalAuthorization(
            PaymentId paymentId,
            com.hyperswitch.common.dto.IncrementalAuthorizationRequest request);
    
    /**
     * Extend authorization - extend the authorization validity period
     */
    Mono<Result<PaymentIntent, PaymentError>> extendAuthorization(PaymentId paymentId);
    
    /**
     * Void a payment - cancel an authorized payment before capture
     */
    Mono<Result<PaymentIntent, PaymentError>> voidPayment(
            PaymentId paymentId,
            com.hyperswitch.common.dto.VoidPaymentRequest request);
    
    /**
     * Schedule capture - schedule automatic capture of an authorized payment
     */
    Mono<Result<PaymentIntent, PaymentError>> scheduleCapture(
            PaymentId paymentId,
            com.hyperswitch.common.dto.ScheduleCaptureRequest request);
    
    /**
     * Approve a payment - approve a payment for processing
     */
    Mono<Result<PaymentIntent, PaymentError>> approvePayment(
            PaymentId paymentId,
            com.hyperswitch.common.dto.ApprovePaymentRequest request);
    
    /**
     * Reject a payment - reject a payment
     */
    Mono<Result<PaymentIntent, PaymentError>> rejectPayment(
            PaymentId paymentId,
            com.hyperswitch.common.dto.RejectPaymentRequest request);
    
    /**
     * Sync payment status with connector (psync)
     */
    Mono<Result<PaymentIntent, PaymentError>> syncPayment(
            PaymentId paymentId,
            com.hyperswitch.common.dto.SyncPaymentRequest request);
    
    /**
     * List payment attempts for a payment
     */
    Mono<Result<reactor.core.publisher.Flux<com.hyperswitch.common.dto.PaymentAttemptResponse>, PaymentError>> listPaymentAttempts(
            PaymentId paymentId,
            String merchantId);
    
    /**
     * List payments with filters
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentListResponse, PaymentError>> listPayments(
            String merchantId,
            com.hyperswitch.common.dto.PaymentListFilterConstraints constraints);
    
    /**
     * Get available payment filters
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentListFiltersResponse, PaymentError>> getPaymentFilters(
            String merchantId);
    
    /**
     * Get payment aggregates (status counts)
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentsAggregateResponse, PaymentError>> getPaymentAggregates(
            String merchantId,
            java.time.Instant startTime,
            java.time.Instant endTime);
    
    /**
     * List refunds with filters
     */
    Mono<Result<com.hyperswitch.common.dto.RefundListResponse, PaymentError>> listRefunds(
            String merchantId,
            com.hyperswitch.common.dto.RefundListFilterConstraints constraints);
    
    /**
     * Get available refund filters
     */
    Mono<Result<com.hyperswitch.common.dto.RefundFiltersResponse, PaymentError>> getRefundFilters(
            String merchantId);
    
    /**
     * Sync refund status with connector
     */
    Mono<Result<Refund, PaymentError>> syncRefund(
            String refundId,
            String paymentId,
            String merchantId,
            Boolean forceSync);
    
    /**
     * Get refund by ID
     */
    Mono<Result<Refund, PaymentError>> getRefund(
            String refundId,
            String merchantId);
    
    /**
     * Update refund manually
     */
    Mono<Result<Refund, PaymentError>> updateRefund(
            String refundId,
            String merchantId,
            com.hyperswitch.common.dto.UpdateRefundRequest request);
    
    /**
     * Get refund aggregates (status counts)
     */
    Mono<Result<com.hyperswitch.common.dto.RefundAggregatesResponse, PaymentError>> getRefundAggregates(
            String merchantId,
            java.time.Instant startTime,
            java.time.Instant endTime);
    
    /**
     * Get payment by merchant reference ID
     */
    Mono<Result<PaymentIntent, PaymentError>> getPaymentByMerchantReferenceId(
            String merchantId,
            String merchantReferenceId);
    
    /**
     * Create refund (v2 API)
     */
    Mono<Result<Refund, PaymentError>> createRefundV2(
            String merchantId,
            com.hyperswitch.common.dto.RefundsCreateRequest request);
    
    /**
     * Retrieve refund (v2 API)
     */
    Mono<Result<Refund, PaymentError>> retrieveRefundV2(
            String refundId,
            String merchantId,
            com.hyperswitch.common.dto.RefundsRetrieveRequest request);
    
    /**
     * Update refund metadata (v2 API)
     */
    Mono<Result<Refund, PaymentError>> updateRefundMetadataV2(
            String refundId,
            String merchantId,
            com.hyperswitch.common.dto.RefundMetadataUpdateRequest request);
    
    /**
     * List refunds (v2 API)
     */
    Mono<Result<com.hyperswitch.common.dto.RefundListResponse, PaymentError>> listRefundsV2(
            String merchantId,
            com.hyperswitch.common.dto.RefundListFilterConstraints constraints);
    
    /**
     * Create payment intent (v2 API)
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentsIntentResponse, PaymentError>> createPaymentIntentV2(
            String merchantId,
            com.hyperswitch.common.dto.PaymentsCreateIntentRequest request);
    
    /**
     * Get payment intent (v2 API)
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentsIntentResponse, PaymentError>> getPaymentIntentV2(
            String paymentId,
            String merchantId);
    
    /**
     * Update payment intent (v2 API)
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentsIntentResponse, PaymentError>> updatePaymentIntentV2(
            String paymentId,
            String merchantId,
            com.hyperswitch.common.dto.PaymentsUpdateIntentRequest request);
    
    /**
     * Confirm payment intent (v2 API)
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentsIntentResponse, PaymentError>> confirmPaymentIntentV2(
            String paymentId,
            String merchantId,
            ConfirmPaymentRequest request);
    
    /**
     * Create and confirm payment intent (v2 API)
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentsIntentResponse, PaymentError>> createAndConfirmPaymentIntentV2(
            String merchantId,
            com.hyperswitch.common.dto.CreatePaymentRequest request);
    
    /**
     * Start payment redirection (v2 API)
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentsStartRedirectionResponse, PaymentError>> startPaymentRedirectionV2(
            String paymentId,
            String merchantId);
    
    /**
     * Finish payment redirection (v2 API)
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentsFinishRedirectionResponse, PaymentError>> finishPaymentRedirectionV2(
            String paymentId,
            String publishableKey,
            String profileId);
    
    /**
     * Create external SDK tokens (v2 API)
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentsSessionResponse, PaymentError>> createExternalSdkTokensV2(
            String paymentId,
            String merchantId,
            com.hyperswitch.common.dto.PaymentsSessionRequest request);
    
    /**
     * Post session tokens (v1 API)
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentsPostSessionTokensResponse, PaymentError>> postSessionTokens(
            String paymentId,
            String merchantId,
            com.hyperswitch.common.dto.PaymentsPostSessionTokensRequest request);
    
    /**
     * Create session tokens (v1 API)
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentsSessionResponse, PaymentError>> createSessionTokens(
            String merchantId,
            com.hyperswitch.common.dto.PaymentsSessionRequest request);
    
    // ========== Payment Redirect Flows (v1) ==========
    
    Mono<Result<PaymentIntent, PaymentError>> handleRedirectResponse(
            String paymentId,
            String merchantId,
            String connector,
            java.util.Map<String, String> queryParams);
    
    Mono<Result<PaymentIntent, PaymentError>> handleRedirectResponseWithCreds(
            String paymentId,
            String merchantId,
            String connector,
            String credsIdentifier,
            java.util.Map<String, String> queryParams);
    
    Mono<Result<PaymentIntent, PaymentError>> completeAuthorizationRedirect(
            String paymentId,
            String merchantId,
            String connector,
            java.util.Map<String, String> queryParams);
    
    Mono<Result<PaymentIntent, PaymentError>> completeAuthorizationRedirectWithCreds(
            String paymentId,
            String merchantId,
            String connector,
            String credsIdentifier,
            java.util.Map<String, String> queryParams);
    
    Mono<Result<PaymentIntent, PaymentError>> completeAuthorize(
            String paymentId,
            String merchantId,
            java.util.Map<String, Object> request);
    
    // ========== Payment Manual Update ==========
    
    Mono<Result<PaymentIntent, PaymentError>> manualUpdatePayment(
            String paymentId,
            String merchantId,
            java.util.Map<String, Object> updateRequest);
    
    // ========== Payment Metadata Update ==========
    
    Mono<Result<PaymentIntent, PaymentError>> updatePaymentMetadata(
            String paymentId,
            String merchantId,
            java.util.Map<String, Object> metadata);
    
    // ========== Payment Dynamic Tax Calculation ==========
    
    Mono<Result<com.hyperswitch.common.dto.TaxCalculationResponse, PaymentError>> calculateTax(
            String paymentId,
            String merchantId,
            com.hyperswitch.common.dto.TaxCalculationRequest request);
    
    // ========== Payment Extended Card Info ==========
    
    Mono<Result<com.hyperswitch.common.dto.ExtendedCardInfoResponse, PaymentError>> getExtendedCardInfo(
            String paymentId,
            String merchantId);
    
    // ========== Payment Eligibility ==========
    
    Mono<Result<com.hyperswitch.common.dto.EligibilityResponse, PaymentError>> checkBalanceAndApplyPmData(
            String paymentId,
            String merchantId,
            com.hyperswitch.common.dto.EligibilityRequest request);
    
    Mono<Result<com.hyperswitch.common.dto.EligibilityResponse, PaymentError>> submitEligibility(
            String paymentId,
            String merchantId,
            com.hyperswitch.common.dto.EligibilityRequest request);
    
    // ========== Payment Cancel Post Capture ==========
    
    Mono<Result<PaymentIntent, PaymentError>> cancelPostCapture(
            String paymentId,
            String merchantId,
            com.hyperswitch.common.dto.CancelPostCaptureRequest request);
    
    // ========== Payment Profile Endpoints ==========
    
    Mono<Result<com.hyperswitch.common.dto.PaymentListResponse, PaymentError>> listPaymentsForProfile(
            String merchantId,
            String profileId,
            Integer limit,
            Integer offset);
    
    Mono<Result<com.hyperswitch.common.dto.PaymentListResponse, PaymentError>> listPaymentsForProfileWithFilters(
            String merchantId,
            com.hyperswitch.common.dto.PaymentListFilterConstraints constraints);
    
    Mono<Result<com.hyperswitch.common.dto.PaymentListFiltersResponse, PaymentError>> getPaymentFiltersForProfile(
            String merchantId,
            String profileId);
    
    Mono<Result<com.hyperswitch.common.dto.PaymentsAggregateResponse, PaymentError>> getPaymentAggregatesForProfile(
            String merchantId,
            String profileId,
            java.time.Instant startTime,
            java.time.Instant endTime);
    
    // ========== Payment Intent v2 API Additional Methods ==========
    
    Mono<Result<com.hyperswitch.common.dto.PaymentsIntentResponse, PaymentError>> proxyConfirmIntent(
            String paymentId,
            String merchantId,
            java.util.Map<String, Object> request);
    
    Mono<Result<com.hyperswitch.common.dto.PaymentsIntentResponse, PaymentError>> confirmIntentWithExternalVaultProxy(
            String paymentId,
            String merchantId,
            java.util.Map<String, Object> request);
    
    Mono<Result<com.hyperswitch.common.dto.PaymentsIntentResponse, PaymentError>> getRevenueRecoveryIntent(
            String paymentId,
            String merchantId);
    
    Mono<Result<com.hyperswitch.common.dto.PaymentsIntentResponse, PaymentError>> getPaymentStatusV2(
            String paymentId,
            String merchantId,
            Boolean forceSync);
    
    Mono<Result<com.hyperswitch.common.dto.PaymentsIntentResponse, PaymentError>> getPaymentIntentByMerchantReferenceIdV2(
            String merchantId,
            String merchantReferenceId);
    
    Mono<Result<com.hyperswitch.common.dto.PaymentsIntentResponse, PaymentError>> createRecoveryPayment(
            String merchantId,
            com.hyperswitch.common.dto.CreatePaymentRequest request);
}


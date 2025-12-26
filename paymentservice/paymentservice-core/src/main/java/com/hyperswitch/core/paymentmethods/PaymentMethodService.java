package com.hyperswitch.core.paymentmethods;

import com.hyperswitch.common.dto.NetworkTokenStatusResponse;
import com.hyperswitch.common.dto.PaymentMethodRequest;
import com.hyperswitch.common.dto.PaymentMethodResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.CustomerId;
import com.hyperswitch.common.types.PaymentMethodId;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Payment method management service
 */
public interface PaymentMethodService {
    
    /**
     * Create a new payment method
     */
    Mono<Result<PaymentMethodResponse, PaymentError>> createPaymentMethod(PaymentMethodRequest request);
    
    /**
     * Get payment method by ID
     */
    Mono<Result<PaymentMethodResponse, PaymentError>> getPaymentMethod(PaymentMethodId paymentMethodId);
    
    /**
     * List payment methods for a customer
     */
    Mono<Result<Flux<PaymentMethodResponse>, PaymentError>> listCustomerPaymentMethods(CustomerId customerId);
    
    /**
     * Set default payment method for a customer
     */
    Mono<Result<Void, PaymentError>> setDefaultPaymentMethod(CustomerId customerId, PaymentMethodId paymentMethodId);
    
    /**
     * Delete payment method
     */
    Mono<Result<Void, PaymentError>> deletePaymentMethod(PaymentMethodId paymentMethodId);
    
    /**
     * Get payment method by client secret
     */
    Mono<Result<PaymentMethodResponse, PaymentError>> getPaymentMethodByClientSecret(String clientSecret);
    
    /**
     * Update saved payment method
     */
    Mono<Result<PaymentMethodResponse, PaymentError>> updatePaymentMethod(
        PaymentMethodId paymentMethodId,
        PaymentMethodRequest request
    );
    
    /**
     * Check network token status for a payment method
     */
    Mono<Result<NetworkTokenStatusResponse, PaymentError>> checkNetworkTokenStatus(PaymentMethodId paymentMethodId);
    
    /**
     * Tokenize a card
     */
    Mono<Result<com.hyperswitch.common.dto.TokenizeCardResponse, PaymentError>> tokenizeCard(
        com.hyperswitch.common.dto.TokenizeCardRequest request
    );
    
    /**
     * List payment methods with optional filters
     */
    Mono<Result<Flux<PaymentMethodResponse>, PaymentError>> listPaymentMethods(
        String merchantId,
        String customerId,
        String paymentMethodType
    );
    
    /**
     * Get payment methods eligible for a payment
     */
    Mono<Result<Flux<PaymentMethodResponse>, PaymentError>> getPaymentMethodsForPayment(
        com.hyperswitch.common.types.PaymentId paymentId,
        String merchantId
    );
    
    /**
     * Get payment method token data
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentMethodTokenResponse, PaymentError>> getPaymentMethodToken(
        PaymentMethodId paymentMethodId,
        String merchantId
    );
    
    /**
     * Get payment method filters (connectors, currencies, countries)
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentMethodFilterResponse, PaymentError>> getPaymentMethodFilters(
        String merchantId,
        String connector
    );
    
    /**
     * Migrate payment method from one connector to another
     */
    Mono<Result<PaymentMethodResponse, PaymentError>> migratePaymentMethod(
        String merchantId,
        com.hyperswitch.common.dto.PaymentMethodMigrateRequest request
    );
    
    /**
     * Batch migrate payment methods
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentMethodBatchMigrateResponse, PaymentError>> batchMigratePaymentMethods(
        String merchantId,
        com.hyperswitch.common.dto.PaymentMethodBatchMigrateRequest request
    );
    
    /**
     * Batch update payment methods
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentMethodBatchUpdateResponse, PaymentError>> batchUpdatePaymentMethods(
        String merchantId,
        com.hyperswitch.common.dto.PaymentMethodBatchUpdateRequest request
    );
    
    /**
     * Batch tokenize cards
     */
    Mono<Result<com.hyperswitch.common.dto.BatchTokenizeCardResponse, PaymentError>> batchTokenizeCards(
        String merchantId,
        com.hyperswitch.common.dto.BatchTokenizeCardRequest request
    );
    
    /**
     * Initiate payment method collect link
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentMethodCollectLinkResponse, PaymentError>> initiatePaymentMethodCollectLink(
        String merchantId,
        com.hyperswitch.common.dto.PaymentMethodCollectLinkRequest request
    );
    
    /**
     * Render payment method collect link
     */
    Mono<Result<String, PaymentError>> renderPaymentMethodCollectLink(
        String merchantId,
        String collectLinkId
    );
    
    /**
     * Create payment method intent (v2 API)
     */
    Mono<Result<PaymentMethodResponse, PaymentError>> createPaymentMethodIntent(
        String merchantId,
        com.hyperswitch.common.dto.PaymentMethodIntentCreateRequest request
    );
    
    /**
     * Confirm payment method intent (v2 API)
     */
    Mono<Result<PaymentMethodResponse, PaymentError>> confirmPaymentMethodIntent(
        String merchantId,
        String paymentMethodId,
        com.hyperswitch.common.dto.PaymentMethodIntentConfirmRequest request
    );
    
    /**
     * Tokenize card using existing payment method
     */
    Mono<Result<com.hyperswitch.common.dto.TokenizeCardResponse, PaymentError>> tokenizeCardUsingPaymentMethod(
        String merchantId,
        PaymentMethodId paymentMethodId,
        com.hyperswitch.common.dto.TokenizeCardRequest request
    );
    
    /**
     * Update payment method (alternative endpoint)
     */
    Mono<Result<PaymentMethodResponse, PaymentError>> updatePaymentMethodV1(
        String merchantId,
        PaymentMethodId paymentMethodId,
        PaymentMethodRequest request
    );
    
    /**
     * Save payment method for future use
     */
    Mono<Result<PaymentMethodResponse, PaymentError>> savePaymentMethod(
        String merchantId,
        PaymentMethodId paymentMethodId
    );
    
    /**
     * Create link token for payment method authentication
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentMethodAuthLinkResponse, PaymentError>> createPaymentMethodAuthLink(
        String merchantId,
        com.hyperswitch.common.dto.PaymentMethodAuthLinkRequest request
    );
    
    /**
     * Exchange token for payment method authentication
     */
    Mono<Result<com.hyperswitch.common.dto.PaymentMethodAuthExchangeResponse, PaymentError>> exchangePaymentMethodAuthToken(
        String merchantId,
        com.hyperswitch.common.dto.PaymentMethodAuthExchangeRequest request
    );
    
    /**
     * Get payment method token data (v2 API)
     */
    Mono<Result<com.hyperswitch.common.dto.TokenDataResponse, PaymentError>> getPaymentMethodTokenData(
        String merchantId,
        PaymentMethodId paymentMethodId,
        com.hyperswitch.common.dto.GetTokenDataRequest request
    );
}


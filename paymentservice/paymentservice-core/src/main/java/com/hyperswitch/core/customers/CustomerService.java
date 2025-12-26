package com.hyperswitch.core.customers;

import com.hyperswitch.common.dto.CustomerRequest;
import com.hyperswitch.common.dto.CustomerResponse;
import com.hyperswitch.common.dto.CustomerListWithCountResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.CustomerId;
import com.hyperswitch.common.types.MerchantId;
import com.hyperswitch.common.types.Result;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Customer management service
 */
public interface CustomerService {
    
    /**
     * Create a new customer
     */
    Mono<Result<CustomerResponse, PaymentError>> createCustomer(CustomerRequest request);
    
    /**
     * Get customer by ID
     */
    Mono<Result<CustomerResponse, PaymentError>> getCustomer(CustomerId customerId);
    
    /**
     * Update customer
     */
    Mono<Result<CustomerResponse, PaymentError>> updateCustomer(CustomerId customerId, CustomerRequest request);
    
    /**
     * Delete customer
     */
    Mono<Result<Void, PaymentError>> deleteCustomer(CustomerId customerId);
    
    /**
     * List customers for a merchant
     */
    Mono<Result<Flux<CustomerResponse>, PaymentError>> listCustomers(MerchantId merchantId, Pageable pageable);
    
    /**
     * List customers with count for a merchant
     */
    Mono<Result<CustomerListWithCountResponse, PaymentError>> listCustomersWithCount(
            MerchantId merchantId,
            Integer limit,
            Integer offset);
    
    /**
     * Get total payment method count for a merchant
     */
    Mono<Result<Long, PaymentError>> getTotalPaymentMethodCount(MerchantId merchantId);
}


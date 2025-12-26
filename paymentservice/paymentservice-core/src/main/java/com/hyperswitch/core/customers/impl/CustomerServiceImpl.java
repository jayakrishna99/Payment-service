package com.hyperswitch.core.customers.impl;

import com.hyperswitch.common.dto.CustomerRequest;
import com.hyperswitch.common.dto.CustomerResponse;
import com.hyperswitch.common.dto.CustomerListWithCountResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.CustomerId;
import com.hyperswitch.common.types.MerchantId;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.customers.CustomerService;
import com.hyperswitch.storage.entity.CustomerEntity;
import com.hyperswitch.storage.repository.CustomerRepository;
import com.hyperswitch.storage.repository.PaymentMethodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of CustomerService
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private static final String CUSTOMER_NOT_FOUND_MSG = "Customer not found";

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper,
            PaymentMethodRepository paymentMethodRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public Mono<Result<CustomerResponse, PaymentError>> createCustomer(CustomerRequest request) {
        log.info("Creating customer for merchant: {}", request.getMerchantId());
        
        return Mono.fromCallable(() -> {
            CustomerId customerId = CustomerId.generate();
            
            return CustomerEntity.builder()
                .id(UUID.randomUUID().toString())
                .customerId(customerId.getValue())
                .merchantId(request.getMerchantId().getValue())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .phoneCountryCode(request.getPhoneCountryCode())
                .description(request.getDescription())
                .metadata(request.getMetadata())
                .addressId(request.getAddressId())
                .defaultPaymentMethodId(request.getDefaultPaymentMethodId())
                .createdAt(Instant.now())
                .modifiedAt(Instant.now())
                .build();
        })
        .flatMap(customerRepository::save)
        .map(saved -> {
            CustomerResponse response = customerMapper.toCustomerResponse(saved);
            return Result.<CustomerResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error creating customer", error);
            return Mono.just(Result.<CustomerResponse, PaymentError>err(PaymentError.of(
                "CUSTOMER_CREATE_FAILED",
                "Failed to create customer: " + error.getMessage()
            )));
        });
    }

    @Override
    public Mono<Result<CustomerResponse, PaymentError>> getCustomer(CustomerId customerId) {
        log.info("Getting customer: {}", customerId);
        
        return customerRepository.findByCustomerId(customerId.getValue())
            .map(customerMapper::toCustomerResponse)
            .map(Result::<CustomerResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<CustomerResponse, PaymentError>err(
                PaymentError.of("CUSTOMER_NOT_FOUND", CUSTOMER_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error getting customer", error);
                return Mono.just(Result.<CustomerResponse, PaymentError>err(PaymentError.of(
                    "CUSTOMER_GET_FAILED",
                    "Failed to get customer: " + error.getMessage()
                )));
            });
    }

    @Override
    public Mono<Result<CustomerResponse, PaymentError>> updateCustomer(CustomerId customerId, CustomerRequest request) {
        log.info("Updating customer: {}", customerId);
        
        return customerRepository.findByCustomerId(customerId.getValue())
            .flatMap(existing -> {
                updateCustomerFields(existing, request);
                existing.setModifiedAt(Instant.now());
                return customerRepository.save(existing);
            })
            .map(customerMapper::toCustomerResponse)
            .map(Result::<CustomerResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<CustomerResponse, PaymentError>err(
                PaymentError.of("CUSTOMER_NOT_FOUND", CUSTOMER_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error updating customer", error);
                return Mono.just(Result.<CustomerResponse, PaymentError>err(PaymentError.of(
                    "CUSTOMER_UPDATE_FAILED",
                    "Failed to update customer: " + error.getMessage()
                )));
            });
    }

    @Override
    public Mono<Result<Void, PaymentError>> deleteCustomer(CustomerId customerId) {
        log.info("Deleting customer: {}", customerId);
        
        return customerRepository.findByCustomerId(customerId.getValue())
            .flatMap(customerRepository::delete)
            .then(Mono.just(Result.<Void, PaymentError>ok(null)))
            .switchIfEmpty(Mono.just(Result.<Void, PaymentError>err(
                PaymentError.of("CUSTOMER_NOT_FOUND", CUSTOMER_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error deleting customer", error);
                return Mono.just(Result.<Void, PaymentError>err(PaymentError.of(
                    "CUSTOMER_DELETE_FAILED",
                    "Failed to delete customer: " + error.getMessage()
                )));
            });
    }

    @Override
    public Mono<Result<Flux<CustomerResponse>, PaymentError>> listCustomers(MerchantId merchantId, Pageable pageable) {
        log.info("Listing customers for merchant: {}", merchantId);
        
        return Mono.fromCallable(() -> {
            Flux<CustomerResponse> customers = customerRepository
                .findByMerchantIdOrderByCreatedAtDesc(merchantId.getValue(), pageable)
                .map(customerMapper::toCustomerResponse);
            
            return Result.<Flux<CustomerResponse>, PaymentError>ok(customers);
        })
        .onErrorResume(error -> {
            log.error("Error listing customers", error);
            return Mono.just(Result.<Flux<CustomerResponse>, PaymentError>err(PaymentError.of(
                "CUSTOMER_LIST_FAILED",
                "Failed to list customers: " + error.getMessage()
            )));
        });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.CustomerListWithCountResponse, PaymentError>> listCustomersWithCount(
            MerchantId merchantId,
            Integer limit,
            Integer offset) {
        log.info("Listing customers with count for merchant: {}, limit: {}, offset: {}", merchantId, limit, offset);
        
        int finalLimit = limit != null ? limit : 100;
        int finalOffset = offset != null ? offset : 0;
        
        return customerRepository.findByMerchantIdOrderByCreatedAtDesc(merchantId.getValue())
            .collectList()
            .flatMap(allCustomers -> {
                long totalCount = allCustomers.size();
                
                List<CustomerResponse> paginatedCustomers = allCustomers.stream()
                    .skip(finalOffset)
                    .limit(finalLimit)
                    .map(customerMapper::toCustomerResponse)
                    .toList();
                
                CustomerListWithCountResponse response = new CustomerListWithCountResponse();
                response.setData(paginatedCustomers);
                response.setTotalCount(totalCount);
                response.setLimit(finalLimit);
                response.setOffset(finalOffset);
                
                return Mono.just(Result.<CustomerListWithCountResponse, PaymentError>ok(response));
            })
            .onErrorResume(error -> {
                log.error("Error listing customers with count", error);
                return Mono.just(Result.<CustomerListWithCountResponse, PaymentError>err(
                    PaymentError.of("CUSTOMER_LIST_WITH_COUNT_FAILED",
                        "Failed to list customers with count: " + error.getMessage())
                ));
            });
    }
    
    /**
     * Update customer entity fields from request
     */
    private void updateCustomerFields(CustomerEntity existing, CustomerRequest request) {
        if (request.getName() != null) {
            existing.setName(request.getName());
        }
        if (request.getEmail() != null) {
            existing.setEmail(request.getEmail());
        }
        if (request.getPhone() != null) {
            existing.setPhone(request.getPhone());
        }
        if (request.getPhoneCountryCode() != null) {
            existing.setPhoneCountryCode(request.getPhoneCountryCode());
        }
        if (request.getDescription() != null) {
            existing.setDescription(request.getDescription());
        }
        if (request.getMetadata() != null) {
            existing.setMetadata(request.getMetadata());
        }
        if (request.getAddressId() != null) {
            existing.setAddressId(request.getAddressId());
        }
        if (request.getDefaultPaymentMethodId() != null) {
            existing.setDefaultPaymentMethodId(request.getDefaultPaymentMethodId());
        }
    }
    
    @Override
    public Mono<Result<Long, PaymentError>> getTotalPaymentMethodCount(MerchantId merchantId) {
        log.info("Getting total payment method count for merchant: {}", merchantId);
        
        return paymentMethodRepository.findByMerchantIdOrderByCreatedAtDesc(merchantId.getValue())
            .count()
            .map(Result::<Long, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error getting total payment method count", error);
                return Mono.just(Result.<Long, PaymentError>err(
                    PaymentError.of("PAYMENT_METHOD_COUNT_FAILED",
                        "Failed to get total payment method count: " + error.getMessage())
                ));
            });
    }
}


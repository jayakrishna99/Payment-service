package com.hyperswitch.core.customers.impl;

import com.hyperswitch.common.dto.CustomerResponse;
import com.hyperswitch.common.types.CustomerId;
import com.hyperswitch.common.types.MerchantId;
import com.hyperswitch.storage.entity.CustomerEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between CustomerEntity and CustomerResponse
 */
@Component
public class CustomerMapper {
    
    public CustomerResponse toCustomerResponse(CustomerEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return CustomerResponse.builder()
            .customerId(CustomerId.of(entity.getCustomerId()))
            .merchantId(MerchantId.of(entity.getMerchantId()))
            .name(entity.getName())
            .email(entity.getEmail())
            .phone(entity.getPhone())
            .phoneCountryCode(entity.getPhoneCountryCode())
            .description(entity.getDescription())
            .metadata(entity.getMetadata())
            .addressId(entity.getAddressId())
            .defaultPaymentMethodId(entity.getDefaultPaymentMethodId())
            .connectorCustomer(entity.getConnectorCustomer())
            .createdAt(entity.getCreatedAt())
            .modifiedAt(entity.getModifiedAt())
            .build();
    }
}


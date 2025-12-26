package com.hyperswitch.core.paymentmethods.impl;

import com.hyperswitch.common.dto.PaymentMethodResponse;
import com.hyperswitch.common.types.CustomerId;
import com.hyperswitch.common.types.MerchantId;
import com.hyperswitch.common.types.PaymentMethodId;
import com.hyperswitch.storage.entity.PaymentMethodEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between PaymentMethodEntity and PaymentMethodResponse
 */
@Component
public class PaymentMethodMapper {
    
    public PaymentMethodResponse toPaymentMethodResponse(PaymentMethodEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return PaymentMethodResponse.builder()
            .paymentMethodId(PaymentMethodId.of(entity.getPaymentMethodId()))
            .customerId(CustomerId.of(entity.getCustomerId()))
            .merchantId(MerchantId.of(entity.getMerchantId()))
            .paymentMethodType(entity.getPaymentMethodType())
            .paymentMethodSubtype(entity.getPaymentMethodSubtype())
            .paymentMethodData(entity.getPaymentMethodData())
            .lockerId(entity.getLockerId())
            .lastUsedAt(entity.getLastUsedAt())
            .status(entity.getStatus())
            .connectorMandateDetails(entity.getConnectorMandateDetails())
            .networkTransactionId(entity.getNetworkTransactionId())
            .clientSecret(entity.getClientSecret())
            .createdAt(entity.getCreatedAt())
            .modifiedAt(entity.getModifiedAt())
            .build();
    }
}


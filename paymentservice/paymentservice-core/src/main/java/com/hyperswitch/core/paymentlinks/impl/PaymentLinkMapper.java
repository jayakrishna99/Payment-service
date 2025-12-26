package com.hyperswitch.core.paymentlinks.impl;

import com.hyperswitch.common.dto.PaymentLinkResponse;
import com.hyperswitch.storage.entity.PaymentLinkEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between PaymentLinkEntity and PaymentLinkResponse
 */
@Component
public class PaymentLinkMapper {
    
    public PaymentLinkResponse toPaymentLinkResponse(PaymentLinkEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return PaymentLinkResponse.builder()
            .paymentLinkId(entity.getPaymentLinkId())
            .paymentId(entity.getPaymentId())
            .linkToPay(entity.getLinkToPay())
            .secureLink(entity.getSecureLink())
            .merchantId(entity.getMerchantId())
            .amount(entity.getAmount())
            .currency(entity.getCurrency())
            .description(entity.getDescription())
            .customMerchantName(entity.getCustomMerchantName())
            .createdAt(entity.getCreatedAt())
            .lastModifiedAt(entity.getLastModifiedAt())
            .fulfilmentTime(entity.getFulfilmentTime())
            .expiresAt(entity.getExpiresAt())
            .paymentLinkConfig(entity.getPaymentLinkConfig())
            .build();
    }
}


package com.hyperswitch.core.mandates.impl;

import com.hyperswitch.common.dto.MandateResponse;
import com.hyperswitch.storage.entity.MandateEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between MandateEntity and MandateResponse
 */
@Component
public class MandateMapper {
    
    public MandateResponse toMandateResponse(MandateEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return MandateResponse.builder()
            .mandateId(entity.getMandateId())
            .customerId(entity.getCustomerId())
            .paymentMethodId(entity.getPaymentMethodId())
            .status(entity.getMandateStatus())
            .type(entity.getMandateType())
            .customerAcceptedAt(entity.getCustomerAcceptedAt())
            .mandateAmount(entity.getMandateAmount())
            .mandateCurrency(entity.getMandateCurrency())
            .startDate(entity.getStartDate())
            .endDate(entity.getEndDate())
            .connectorMandateId(entity.getConnectorMandateId())
            .metadata(entity.getMetadata())
            .createdAt(entity.getCreatedAt())
            .build();
    }
}


package com.hyperswitch.core.payouts.impl;

import com.hyperswitch.common.dto.PayoutResponse;
import com.hyperswitch.common.types.PayoutStatus;
import com.hyperswitch.common.types.PayoutType;
import com.hyperswitch.storage.entity.PayoutEntity;

/**
 * Mapper for converting between PayoutEntity and PayoutResponse
 */
public class PayoutMapper {

    private PayoutMapper() {
        // Utility class - prevent instantiation
    }

    public static PayoutResponse toPayoutResponse(PayoutEntity entity) {
        PayoutResponse response = new PayoutResponse();
        response.setPayoutId(entity.getPayoutId());
        response.setMerchantId(entity.getMerchantId());
        response.setCustomerId(entity.getCustomerId());
        response.setAddressId(entity.getAddressId());
        if (entity.getPayoutType() != null) {
            response.setPayoutType(PayoutType.valueOf(entity.getPayoutType()));
        }
        response.setPayoutMethodId(entity.getPayoutMethodId());
        response.setAmount(entity.getAmount());
        response.setDestinationCurrency(entity.getDestinationCurrency());
        response.setSourceCurrency(entity.getSourceCurrency());
        response.setDescription(entity.getDescription());
        response.setRecurring(entity.getRecurring());
        response.setAutoFulfill(entity.getAutoFulfill());
        response.setReturnUrl(entity.getReturnUrl());
        response.setEntityType(entity.getEntityType());
        response.setMetadata(entity.getMetadata());
        if (entity.getStatus() != null) {
            response.setStatus(PayoutStatus.valueOf(entity.getStatus()));
        }
        response.setCreatedAt(entity.getCreatedAt());
        response.setLastModifiedAt(entity.getLastModifiedAt());
        response.setAttemptCount(entity.getAttemptCount());
        response.setProfileId(entity.getProfileId());
        response.setClientSecret(entity.getClientSecret());
        response.setOrganizationId(entity.getOrganizationId());
        return response;
    }
}


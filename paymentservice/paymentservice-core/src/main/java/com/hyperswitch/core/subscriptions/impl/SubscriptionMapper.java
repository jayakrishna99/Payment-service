package com.hyperswitch.core.subscriptions.impl;

import com.hyperswitch.common.dto.SubscriptionResponse;
import com.hyperswitch.common.types.SubscriptionStatus;
import com.hyperswitch.storage.entity.SubscriptionEntity;

/**
 * Mapper for converting between SubscriptionEntity and SubscriptionResponse
 */
public class SubscriptionMapper {

    public static SubscriptionResponse toSubscriptionResponse(SubscriptionEntity entity) {
        SubscriptionResponse response = new SubscriptionResponse();
        response.setSubscriptionId(entity.getSubscriptionId());
        if (entity.getStatus() != null) {
            response.setStatus(SubscriptionStatus.valueOf(entity.getStatus()));
        }
        response.setBillingProcessor(entity.getBillingProcessor());
        response.setPaymentMethodId(entity.getPaymentMethodId());
        response.setMerchantConnectorId(entity.getMerchantConnectorId());
        response.setClientSecret(entity.getClientSecret());
        response.setConnectorSubscriptionId(entity.getConnectorSubscriptionId());
        response.setMerchantId(entity.getMerchantId());
        response.setCustomerId(entity.getCustomerId());
        response.setMetadata(entity.getMetadata());
        response.setCreatedAt(entity.getCreatedAt());
        response.setModifiedAt(entity.getModifiedAt());
        response.setProfileId(entity.getProfileId());
        response.setMerchantReferenceId(entity.getMerchantReferenceId());
        response.setPlanId(entity.getPlanId());
        response.setItemPriceId(entity.getItemPriceId());
        return response;
    }
}


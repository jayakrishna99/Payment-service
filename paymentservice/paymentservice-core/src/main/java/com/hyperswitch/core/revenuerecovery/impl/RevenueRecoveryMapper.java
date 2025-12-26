package com.hyperswitch.core.revenuerecovery.impl;

import com.hyperswitch.common.dto.RevenueRecoveryResponse;
import com.hyperswitch.common.types.RecoveryStatus;
import com.hyperswitch.common.types.RevenueRecoveryAlgorithmType;
import com.hyperswitch.storage.entity.RevenueRecoveryEntity;

/**
 * Mapper for converting between RevenueRecoveryEntity and RevenueRecoveryResponse
 */
public class RevenueRecoveryMapper {

    public static RevenueRecoveryResponse toRevenueRecoveryResponse(RevenueRecoveryEntity entity) {
        RevenueRecoveryResponse response = new RevenueRecoveryResponse();
        response.setRecoveryId(entity.getRecoveryId());
        response.setMerchantId(entity.getMerchantId());
        response.setPaymentId(entity.getPaymentId());
        response.setAttemptId(entity.getAttemptId());
        response.setProfileId(entity.getProfileId());
        response.setBillingMcaId(entity.getBillingMcaId());
        if (entity.getRecoveryStatus() != null) {
            response.setRecoveryStatus(RecoveryStatus.valueOf(entity.getRecoveryStatus()));
        }
        if (entity.getRetryAlgorithm() != null) {
            response.setRetryAlgorithm(RevenueRecoveryAlgorithmType.valueOf(entity.getRetryAlgorithm()));
        }
        response.setRetryCount(entity.getRetryCount());
        response.setMaxRetries(entity.getMaxRetries());
        response.setRetryBudget(entity.getRetryBudget());
        response.setRetryBudgetUsed(entity.getRetryBudgetUsed());
        response.setNextRetryAt(entity.getNextRetryAt());
        response.setLastErrorCode(entity.getLastErrorCode());
        response.setLastErrorMessage(entity.getLastErrorMessage());
        response.setRecoveryMetadata(entity.getRecoveryMetadata());
        response.setCreatedAt(entity.getCreatedAt());
        response.setModifiedAt(entity.getModifiedAt());
        return response;
    }
}


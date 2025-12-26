package com.hyperswitch.core.disputes.impl;

import com.hyperswitch.common.dto.DisputeResponse;
import com.hyperswitch.common.types.DisputeStage;
import com.hyperswitch.common.types.DisputeStatus;
import com.hyperswitch.storage.entity.DisputeEntity;

/**
 * Mapper for converting between DisputeEntity and DisputeResponse
 */
public class DisputeMapper {

    public static DisputeResponse toDisputeResponse(DisputeEntity entity) {
        DisputeResponse response = new DisputeResponse();
        response.setDisputeId(entity.getDisputeId());
        response.setPaymentId(entity.getPaymentId());
        response.setAttemptId(entity.getAttemptId());
        response.setAmount(entity.getAmount());
        response.setCurrency(entity.getCurrency());
        response.setDisputeStage(DisputeStage.valueOf(entity.getDisputeStage()));
        response.setDisputeStatus(DisputeStatus.valueOf(entity.getDisputeStatus()));
        response.setConnector(entity.getConnector());
        response.setConnectorStatus(entity.getConnectorStatus());
        response.setConnectorDisputeId(entity.getConnectorDisputeId());
        response.setConnectorReason(entity.getConnectorReason());
        response.setConnectorReasonCode(entity.getConnectorReasonCode());
        response.setChallengeRequiredBy(entity.getChallengeRequiredBy());
        response.setConnectorCreatedAt(entity.getConnectorCreatedAt());
        response.setConnectorUpdatedAt(entity.getConnectorUpdatedAt());
        response.setCreatedAt(entity.getCreatedAt());
        response.setProfileId(entity.getProfileId());
        response.setMerchantConnectorId(entity.getMerchantConnectorId());
        return response;
    }
}


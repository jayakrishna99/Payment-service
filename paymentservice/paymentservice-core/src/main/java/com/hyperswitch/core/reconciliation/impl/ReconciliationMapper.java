package com.hyperswitch.core.reconciliation.impl;

import com.hyperswitch.common.dto.ReconciliationResponse;
import com.hyperswitch.storage.entity.ReconciliationEntity;

/**
 * Mapper for converting between ReconciliationEntity and ReconciliationResponse
 */
public class ReconciliationMapper {

    public static ReconciliationResponse toReconciliationResponse(ReconciliationEntity entity) {
        ReconciliationResponse response = new ReconciliationResponse();
        response.setReconciliationId(entity.getReconciliationId());
        response.setMerchantId(entity.getMerchantId());
        response.setReconciliationType(entity.getReconciliationType());
        response.setStatus(entity.getStatus());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setTotalTransactions(entity.getTotalTransactions());
        response.setMatchedTransactions(entity.getMatchedTransactions());
        response.setUnmatchedTransactions(entity.getUnmatchedTransactions());
        response.setDiscrepancies(entity.getDiscrepancies());
        response.setConnectorId(entity.getConnectorId());
        response.setReconciliationData(entity.getReconciliationData());
        response.setCreatedAt(entity.getCreatedAt());
        response.setModifiedAt(entity.getModifiedAt());
        return response;
    }
}


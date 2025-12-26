package com.hyperswitch.core.fraudcheck.impl;

import com.hyperswitch.common.dto.FraudCheckResponse;
import com.hyperswitch.common.types.FraudCheckStatus;
import com.hyperswitch.common.types.FraudCheckType;
import com.hyperswitch.storage.entity.FraudCheckEntity;

/**
 * Mapper for converting between FraudCheckEntity and FraudCheckResponse
 */
public class FraudCheckMapper {

    public static FraudCheckResponse toFraudCheckResponse(FraudCheckEntity entity) {
        FraudCheckResponse response = new FraudCheckResponse();
        response.setFrmId(entity.getFrmId());
        response.setPaymentId(entity.getPaymentId());
        response.setMerchantId(entity.getMerchantId());
        response.setAttemptId(entity.getAttemptId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setFrmName(entity.getFrmName());
        response.setFrmTransactionId(entity.getFrmTransactionId());
        if (entity.getFrmTransactionType() != null) {
            response.setFrmTransactionType(FraudCheckType.valueOf(entity.getFrmTransactionType()));
        }
        if (entity.getFrmStatus() != null) {
            response.setFrmStatus(FraudCheckStatus.valueOf(entity.getFrmStatus()));
        }
        response.setFrmScore(entity.getFrmScore());
        response.setFrmReason(entity.getFrmReason());
        response.setFrmError(entity.getFrmError());
        response.setPaymentDetails(entity.getPaymentDetails());
        response.setMetadata(entity.getMetadata());
        response.setModifiedAt(entity.getModifiedAt());
        return response;
    }
}


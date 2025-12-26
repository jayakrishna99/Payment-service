package com.hyperswitch.core.paymentmethods.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyperswitch.common.dto.Address;
import com.hyperswitch.common.dto.PaymentMethodSessionResponse;
import com.hyperswitch.storage.entity.PaymentMethodSessionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting between PaymentMethodSessionEntity and PaymentMethodSessionResponse
 */
@Component
public class PaymentMethodSessionMapper {
    
    private static final Logger log = LoggerFactory.getLogger(PaymentMethodSessionMapper.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * Convert PaymentMethodSessionEntity to PaymentMethodSessionResponse
     */
    public PaymentMethodSessionResponse toPaymentMethodSessionResponse(PaymentMethodSessionEntity entity) {
        PaymentMethodSessionResponse response = new PaymentMethodSessionResponse();
        response.setId(entity.getId());
        response.setCustomerId(entity.getCustomerId());
        
        // Convert billing from Map to Address if present
        if (entity.getBilling() != null) {
            try {
                Address billing = objectMapper.convertValue(entity.getBilling(), Address.class);
                response.setBilling(billing);
            } catch (Exception e) {
                log.warn("Failed to convert billing address: {}", e.getMessage());
            }
        }
        
        response.setPspTokenization(entity.getPspTokenization());
        response.setNetworkTokenization(entity.getNetworkTokenization());
        response.setTokenizationData(entity.getTokenizationData());
        response.setReturnUrl(entity.getReturnUrl());
        response.setExpiresAt(entity.getExpiresAt());
        response.setClientSecret(entity.getClientSecret());
        response.setNextAction(entity.getNextAction());
        response.setAuthenticationDetails(entity.getAuthenticationDetails());
        response.setAssociatedPaymentMethods(entity.getAssociatedPaymentMethods());
        response.setAssociatedTokenId(entity.getAssociatedTokenId());
        response.setStorageType(entity.getStorageType());
        
        return response;
    }
    
    /**
     * Convert Address to Map for storage
     */
    public Map<String, Object> addressToMap(Address address) {
        if (address == null) {
            return null;
        }
        try {
            return objectMapper.convertValue(address, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            log.warn("Failed to convert address to map: {}", e.getMessage());
            return new HashMap<>();
        }
    }
}


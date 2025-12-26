package com.hyperswitch.core.featurematrix.impl;

import com.hyperswitch.common.dto.FeatureMatrixResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.featurematrix.FeatureMatrixService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of FeatureMatrixService
 */
@Service
public class FeatureMatrixServiceImpl implements FeatureMatrixService {
    
    private static final Logger log = LoggerFactory.getLogger(FeatureMatrixServiceImpl.class);
    
    @Override
    public Mono<Result<FeatureMatrixResponse, PaymentError>> getFeatureMatrix() {
        log.info("Getting feature matrix for connectors");
        
        return Mono.fromCallable(() -> {
            FeatureMatrixResponse response = new FeatureMatrixResponse();
            Map<String, FeatureMatrixResponse.ConnectorFeatures> connectors = new HashMap<>();
            
            // In production, this would:
            // 1. Query connector metadata from database
            // 2. Aggregate features from connector configurations
            // 3. Return comprehensive feature matrix
            
            // Example connector features
            FeatureMatrixResponse.ConnectorFeatures stripeFeatures = new FeatureMatrixResponse.ConnectorFeatures();
            stripeFeatures.setPaymentMethods(Arrays.asList("card", "wallet"));
            stripeFeatures.setCurrencies(Arrays.asList("USD", "EUR", "GBP"));
            stripeFeatures.setCountries(Arrays.asList("US", "GB", "EU"));
            Map<String, Boolean> stripeFeatureMap = new HashMap<>();
            stripeFeatureMap.put("3ds", Boolean.TRUE);
            stripeFeatureMap.put("refunds", Boolean.TRUE);
            stripeFeatureMap.put("capture", Boolean.TRUE);
            stripeFeatures.setFeatures(stripeFeatureMap);
            Map<String, Boolean> stripeCapabilities = new HashMap<>();
            stripeCapabilities.put("recurring", Boolean.TRUE);
            stripeCapabilities.put("tokenization", Boolean.TRUE);
            stripeFeatures.setCapabilities(stripeCapabilities);
            connectors.put("stripe", stripeFeatures);
            
            response.setConnectors(connectors);
            
            return Result.<FeatureMatrixResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting feature matrix: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("FEATURE_MATRIX_RETRIEVAL_FAILED",
                "Failed to get feature matrix: " + error.getMessage())));
        });
    }
}


package com.hyperswitch.core.threeds.impl;

import com.hyperswitch.common.dto.ThreeDsDecisionRuleExecuteRequest;
import com.hyperswitch.common.dto.ThreeDsDecisionRuleExecuteResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.common.types.ThreeDSDecision;
import com.hyperswitch.core.threeds.ThreeDsDecisionRuleService;
import com.hyperswitch.storage.entity.RoutingAlgorithmEntity;
import com.hyperswitch.storage.repository.RoutingAlgorithmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Implementation of ThreeDsDecisionRuleService
 */
@Service
public class ThreeDsDecisionRuleServiceImpl implements ThreeDsDecisionRuleService {
    
    private static final Logger log = LoggerFactory.getLogger(ThreeDsDecisionRuleServiceImpl.class);
    
    // PSD2 countries list
    private static final List<String> PSD2_COUNTRIES = Arrays.asList(
        "AT", "BE", "BG", "HR", "CY", "CZ", "DK", "EE", "FI", "FR",
        "DE", "GR", "HU", "IE", "IT", "LV", "LT", "LU", "MT", "NL",
        "PL", "PT", "RO", "SK", "SI", "ES", "SE"
    );
    
    private static final String THREE_DS_DECISION_RULE_KIND = "three_ds_decision_rule";
    private static final long LOW_VALUE_TRANSACTION_THRESHOLD_CENTS = 3000L; // $30.00 in cents
    
    private final RoutingAlgorithmRepository routingAlgorithmRepository;
    
    @Autowired
    public ThreeDsDecisionRuleServiceImpl(RoutingAlgorithmRepository routingAlgorithmRepository) {
        this.routingAlgorithmRepository = routingAlgorithmRepository;
    }
    
    @Override
    public Mono<Result<ThreeDsDecisionRuleExecuteResponse, PaymentError>> executeDecisionRule(
            String merchantId,
            ThreeDsDecisionRuleExecuteRequest request) {
        
        if (request == null) {
            log.warn("Request is null for merchant: {}", merchantId);
            return Mono.just(Result.err(PaymentError.of("INVALID_REQUEST", "Request cannot be null")));
        }
        
        if (request.getRoutingId() == null || request.getRoutingId().isEmpty()) {
            log.warn("Routing ID is null or empty for merchant: {}", merchantId);
            return Mono.just(Result.err(PaymentError.of("INVALID_REQUEST", "Routing ID cannot be null or empty")));
        }
        
        log.info("Executing 3DS decision rule for merchant: {}, routing_id: {}", 
                merchantId, request.getRoutingId());
        
        // Retrieve the routing algorithm
        return routingAlgorithmRepository.findByAlgorithmIdAndMerchantId(
                request.getRoutingId(), merchantId)
            .defaultIfEmpty((RoutingAlgorithmEntity) null)
            .flatMap(algorithm -> {
                if (algorithm == null) {
                    log.warn("Routing algorithm not found: {} for merchant: {}", 
                            request.getRoutingId(), merchantId);
                    return Mono.just(Result.<ThreeDsDecisionRuleExecuteResponse, PaymentError>err(
                        PaymentError.of("ROUTING_ALGORITHM_NOT_FOUND", "Routing algorithm not found")));
                }
                
                // Check if algorithm is a 3DS decision rule type
                String algorithmKind = algorithm.getKind();
                if (algorithmKind == null || !THREE_DS_DECISION_RULE_KIND.equals(algorithmKind)) {
                    log.warn("Algorithm is not a 3DS decision rule: {}", algorithmKind);
                    return Mono.just(Result.<ThreeDsDecisionRuleExecuteResponse, PaymentError>err(
                        PaymentError.of("INVALID_ALGORITHM_TYPE", "Algorithm is not a 3DS decision rule")));
                }
                
                // TODO: In production, parse and execute the algorithm program using DSL interpreter
                // For now, we apply basic PSD2 validations and return a default decision
                
                ThreeDSDecision decision = evaluateDecision(request, algorithm);
                
                // Apply PSD2 validations
                ThreeDSDecision finalDecision = applyPsd2Validations(decision, request);
                
                ThreeDsDecisionRuleExecuteResponse response = new ThreeDsDecisionRuleExecuteResponse();
                response.setDecision(finalDecision.getValue());
                
                return Mono.just(Result.<ThreeDsDecisionRuleExecuteResponse, PaymentError>ok(response));
            })
            .onErrorResume(Throwable.class, error -> {
                log.error("Error executing 3DS decision rule: {}", error.getMessage(), error);
                return Mono.<Result<ThreeDsDecisionRuleExecuteResponse, PaymentError>>just(
                    Result.err(PaymentError.of("THREE_DS_DECISION_RULE_EXECUTION_FAILED", 
                        "Failed to execute 3DS decision rule")));
            });
    }
    
    private ThreeDSDecision evaluateDecision(
            ThreeDsDecisionRuleExecuteRequest request, 
            RoutingAlgorithmEntity algorithm) {
        
        // TODO: In production, parse algorithm_data and execute the DSL program
        // For now, return a default decision based on basic rules
        
        Map<String, Object> algorithmData = algorithm.getAlgorithmData();
        if (algorithmData != null && algorithmData.containsKey("default_selection")) {
            Object defaultSelection = algorithmData.get("default_selection");
            if (defaultSelection instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> defaultMap = (Map<String, Object>) defaultSelection;
                if (defaultMap.containsKey("decision")) {
                    String decisionStr = defaultMap.get("decision").toString();
                    return ThreeDSDecision.fromString(decisionStr);
                }
            }
        }
        
        // Default decision based on payment amount
        // For low value transactions, prefer no 3DS
        if (request.getPayment() != null 
                && request.getPayment().getAmount() != null 
                && request.getPayment().getAmount() < LOW_VALUE_TRANSACTION_THRESHOLD_CENTS) {
            return ThreeDSDecision.THREE_DS_EXEMPTION_REQUESTED_LOW_VALUE;
        }
        
        return ThreeDSDecision.CHALLENGE_PREFERRED;
    }
    
    private ThreeDSDecision applyPsd2Validations(
            ThreeDSDecision decision, 
            ThreeDsDecisionRuleExecuteRequest request) {
        
        boolean issuerInPsd2 = false;
        boolean acquirerInPsd2 = false;
        
        if (request.getIssuer() != null && request.getIssuer().getCountry() != null) {
            issuerInPsd2 = PSD2_COUNTRIES.contains(request.getIssuer().getCountry().toUpperCase());
        }
        
        if (request.getAcquirer() != null && request.getAcquirer().getCountry() != null) {
            acquirerInPsd2 = PSD2_COUNTRIES.contains(request.getAcquirer().getCountry().toUpperCase());
        }
        
        if (issuerInPsd2 && acquirerInPsd2) {
            // If both issuer and acquirer are in PSD2 region
            // Override NO_THREE_DS to enforce 3DS
            if (decision == ThreeDSDecision.NO_THREE_DS) {
                return ThreeDSDecision.CHALLENGE_REQUESTED;
            }
            return decision;
        }
        // If PSD2 doesn't apply, exemptions cannot be applied
        if (decision == ThreeDSDecision.NO_THREE_DS) {
            return ThreeDSDecision.NO_THREE_DS;
        }
        // For all other decisions (including exemptions), enforce challenge
        return ThreeDSDecision.CHALLENGE_REQUESTED;
    }
}


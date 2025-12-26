package com.hyperswitch.core.fraudcheck;

import com.hyperswitch.common.dto.CreatePaymentRequest;
import java.util.Map;

/**
 * Fraud rules engine for evaluating payment risk
 */
public interface FraudRulesEngine {
    
    /**
     * Evaluate payment against fraud rules and calculate risk score
     * @param paymentRequest Payment request to evaluate
     * @param paymentMetadata Additional payment metadata
     * @return Risk score (0-100, higher = more risky)
     */
    int calculateRiskScore(CreatePaymentRequest paymentRequest, Map<String, Object> paymentMetadata);
    
    /**
     * Check if payment should be blocked based on rules
     * @param riskScore Calculated risk score
     * @param merchantId Merchant ID for merchant-specific rules
     * @return true if payment should be blocked
     */
    boolean shouldBlockPayment(int riskScore, String merchantId);
    
    /**
     * Check if payment requires manual review
     * @param riskScore Calculated risk score
     * @param merchantId Merchant ID for merchant-specific rules
     * @return true if payment requires manual review
     */
    boolean requiresManualReview(int riskScore, String merchantId);
}


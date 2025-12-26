package com.hyperswitch.core.fraudcheck.impl;

import com.hyperswitch.common.dto.CreatePaymentRequest;
import com.hyperswitch.common.types.Amount;
import com.hyperswitch.core.fraudcheck.FraudRulesEngine;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Implementation of fraud rules engine
 */
@Component
public class FraudRulesEngineImpl implements FraudRulesEngine {

    // Risk thresholds
    private static final int BLOCK_THRESHOLD = 80;
    private static final int MANUAL_REVIEW_THRESHOLD = 50;
    
    // Amount thresholds (in smallest currency unit)
    private static final long HIGH_AMOUNT_THRESHOLD = 1000000L; // $10,000
    private static final long VERY_HIGH_AMOUNT_THRESHOLD = 5000000L; // $50,000
    
    // Patterns for suspicious data
    private static final Pattern SUSPICIOUS_EMAIL_PATTERN = Pattern.compile(
        ".*(test|fake|temp|spam|dummy|invalid).*", Pattern.CASE_INSENSITIVE
    );
    private static final Pattern SUSPICIOUS_CARD_PATTERN = Pattern.compile(
        "^(4242|4000|5555|3782|3714).*" // Test card patterns
    );

    @Override
    public int calculateRiskScore(CreatePaymentRequest paymentRequest, Map<String, Object> paymentMetadata) {
        int riskScore = 0;
        
        riskScore += calculateAmountRisk(paymentRequest);
        riskScore += calculateEmailRisk(paymentMetadata);
        riskScore += calculateCardRisk(paymentMetadata);
        riskScore += calculateVelocityRisk(paymentMetadata);
        riskScore += calculateIpRisk(paymentMetadata);
        riskScore += calculateGeographicRisk(paymentMetadata);
        riskScore += calculateDeviceRisk(paymentMetadata);
        
        // Cap at 100
        return Math.min(riskScore, 100);
    }
    
    private int calculateAmountRisk(CreatePaymentRequest paymentRequest) {
        if (paymentRequest.getAmount() == null) {
            return 0;
        }
        
        Amount amount = paymentRequest.getAmount();
        long amountValue = amount.getValue().multiply(BigDecimal.valueOf(100)).longValue();
        
        if (amountValue >= VERY_HIGH_AMOUNT_THRESHOLD) {
            return 30;
        } else if (amountValue >= HIGH_AMOUNT_THRESHOLD) {
            return 15;
        }
        return 0;
    }
    
    private int calculateEmailRisk(Map<String, Object> paymentMetadata) {
        if (paymentMetadata == null || !paymentMetadata.containsKey("email")) {
            return 0;
        }
        
        Object emailObj = paymentMetadata.get("email");
        if (emailObj instanceof String email 
                && SUSPICIOUS_EMAIL_PATTERN.matcher(email.toLowerCase()).matches()) {
            return 20;
        }
        return 0;
    }
    
    private int calculateCardRisk(Map<String, Object> paymentMetadata) {
        if (paymentMetadata == null || !paymentMetadata.containsKey("card_number")) {
            return 0;
        }
        
        Object cardObj = paymentMetadata.get("card_number");
        if (cardObj instanceof String cardNumber 
                && SUSPICIOUS_CARD_PATTERN.matcher(cardNumber).matches()) {
            return 25;
        }
        return 0;
    }
    
    private int calculateVelocityRisk(Map<String, Object> paymentMetadata) {
        if (paymentMetadata == null) {
            return 0;
        }
        
        Object recentTransactions = paymentMetadata.get("recent_transaction_count");
        if (recentTransactions instanceof Number number) {
            int count = number.intValue();
            if (count > 10) {
                return 20; // High velocity
            } else if (count > 5) {
                return 10; // Medium velocity
            }
        }
        return 0;
    }
    
    private int calculateIpRisk(Map<String, Object> paymentMetadata) {
        if (paymentMetadata == null) {
            return 0;
        }
        
        Object ipRisk = paymentMetadata.get("ip_risk_score");
        if (ipRisk instanceof Number number) {
            return number.intValue();
        }
        return 0;
    }
    
    private int calculateGeographicRisk(Map<String, Object> paymentMetadata) {
        if (paymentMetadata == null || !paymentMetadata.containsKey("country_mismatch")) {
            return 0;
        }
        
        Object countryMismatchObj = paymentMetadata.get("country_mismatch");
        if (Boolean.TRUE.equals(countryMismatchObj)) {
            return 15;
        }
        return 0;
    }
    
    private int calculateDeviceRisk(Map<String, Object> paymentMetadata) {
        if (paymentMetadata == null || !paymentMetadata.containsKey("device_risk_score")) {
            return 0;
        }
        
        Object deviceRisk = paymentMetadata.get("device_risk_score");
        if (deviceRisk instanceof Number number) {
            return number.intValue();
        }
        return 0;
    }

    @Override
    public boolean shouldBlockPayment(int riskScore, @SuppressWarnings("unused") String merchantId) {
        // Merchants can have custom thresholds, but default is 80
        // In production, merchantId would be used to fetch custom threshold
        return riskScore >= BLOCK_THRESHOLD;
    }

    @Override
    public boolean requiresManualReview(int riskScore, @SuppressWarnings("unused") String merchantId) {
        // Merchants can have custom thresholds, but default is 50
        // In production, merchantId would be used to fetch custom threshold
        return riskScore >= MANUAL_REVIEW_THRESHOLD && riskScore < BLOCK_THRESHOLD;
    }
}


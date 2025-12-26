package com.hyperswitch.core.threeds;

import com.hyperswitch.common.dto.ThreeDsDecisionRuleExecuteRequest;
import com.hyperswitch.common.dto.ThreeDsDecisionRuleExecuteResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for 3DS decision rule operations
 */
public interface ThreeDsDecisionRuleService {
    
    /**
     * Execute 3DS decision rule
     */
    Mono<Result<ThreeDsDecisionRuleExecuteResponse, PaymentError>> executeDecisionRule(
            String merchantId,
            ThreeDsDecisionRuleExecuteRequest request);
}


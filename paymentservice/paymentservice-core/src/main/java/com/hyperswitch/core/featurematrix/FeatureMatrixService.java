package com.hyperswitch.core.featurematrix;

import com.hyperswitch.common.dto.FeatureMatrixResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for feature matrix operations
 */
public interface FeatureMatrixService {
    
    /**
     * Get feature matrix
     */
    Mono<Result<FeatureMatrixResponse, PaymentError>> getFeatureMatrix();
}


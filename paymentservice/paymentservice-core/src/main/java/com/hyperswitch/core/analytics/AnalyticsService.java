package com.hyperswitch.core.analytics;

import com.hyperswitch.common.dto.DomainInfoResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.AnalyticsDomain;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for analytics operations
 */
public interface AnalyticsService {
    
    /**
     * Get domain info
     */
    Mono<Result<DomainInfoResponse, PaymentError>> getDomainInfo(AnalyticsDomain domain);
    
    /**
     * Get merchant domain info
     */
    Mono<Result<DomainInfoResponse, PaymentError>> getMerchantDomainInfo(String merchantId, AnalyticsDomain domain);
    
    /**
     * Get org domain info
     */
    Mono<Result<DomainInfoResponse, PaymentError>> getOrgDomainInfo(String orgId, AnalyticsDomain domain);
    
    /**
     * Get profile domain info
     */
    Mono<Result<DomainInfoResponse, PaymentError>> getProfileDomainInfo(String profileId, AnalyticsDomain domain);
    
    /**
     * Global search
     */
    Mono<Result<com.hyperswitch.common.dto.SearchResponse, PaymentError>> globalSearch(
            String merchantId,
            com.hyperswitch.common.dto.SearchRequest request);
    
    /**
     * Domain-specific search
     */
    Mono<Result<com.hyperswitch.common.dto.SearchResponse, PaymentError>> domainSearch(
            String merchantId,
            AnalyticsDomain domain,
            com.hyperswitch.common.dto.SearchRequest request);
}


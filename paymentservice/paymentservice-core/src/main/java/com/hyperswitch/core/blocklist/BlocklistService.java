package com.hyperswitch.core.blocklist;

import com.hyperswitch.common.dto.BlocklistRequest;
import com.hyperswitch.common.dto.BlocklistResponse;
import com.hyperswitch.common.dto.BlocklistToggleRequest;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for blocklist operations
 */
public interface BlocklistService {
    
    /**
     * List blocked payment methods
     */
    Mono<Result<BlocklistResponse, PaymentError>> listBlocklist(
            String merchantId,
            String dataKind);
    
    /**
     * Add entry to blocklist
     */
    Mono<Result<BlocklistResponse.BlocklistEntry, PaymentError>> addToBlocklist(
            String merchantId,
            BlocklistRequest request);
    
    /**
     * Remove entry from blocklist
     */
    Mono<Result<Void, PaymentError>> removeFromBlocklist(
            String merchantId,
            String fingerprintId);
    
    /**
     * Toggle blocklist guard
     */
    Mono<Result<Void, PaymentError>> toggleBlocklistGuard(
            String merchantId,
            BlocklistToggleRequest request);
}


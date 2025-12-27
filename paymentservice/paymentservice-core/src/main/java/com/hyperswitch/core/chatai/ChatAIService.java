package com.hyperswitch.core.chatai;

import com.hyperswitch.common.dto.ChatAIDataRequest;
import com.hyperswitch.common.dto.ChatAIDataResponse;
import com.hyperswitch.common.dto.ConversationResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for Chat AI operations
 */
public interface ChatAIService {
    
    /**
     * Get data from Hyperswitch AI workflow
     */
    Mono<Result<ChatAIDataResponse, PaymentError>> getAIData(
            String merchantId,
            ChatAIDataRequest request);
    
    /**
     * List all conversations
     */
    Mono<Result<Flux<ConversationResponse>, PaymentError>> listConversations(String merchantId);
}


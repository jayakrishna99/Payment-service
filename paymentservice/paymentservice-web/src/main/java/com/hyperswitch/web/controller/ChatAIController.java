package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.ChatAIDataRequest;
import com.hyperswitch.common.dto.ChatAIDataResponse;
import com.hyperswitch.common.dto.ConversationResponse;
import com.hyperswitch.core.chatai.ChatAIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for Chat AI operations
 */
@RestController
@RequestMapping("/api/chat/ai")
@Tag(name = "Chat AI", description = "Chat AI workflow operations")
public class ChatAIController {
    
    private final ChatAIService chatAIService;
    
    @Autowired
    public ChatAIController(ChatAIService chatAIService) {
        this.chatAIService = chatAIService;
    }
    
    /**
     * Get data from Hyperswitch AI workflow
     * POST /api/chat/ai/data
     */
    @PostMapping("/data")
    @Operation(
        summary = "Get AI data",
        description = "Retrieves data from Hyperswitch AI workflow based on query"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "AI data retrieved successfully",
            content = @Content(schema = @Schema(implementation = ChatAIDataResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ChatAIDataResponse>> getAIData(
            @RequestHeader("merchant_id") String merchantId,
            @RequestBody ChatAIDataRequest request) {
        return chatAIService.getAIData(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * List all conversations
     * GET /api/chat/ai/list
     */
    @GetMapping("/list")
    @Operation(
        summary = "List conversations",
        description = "Lists all AI conversations for the merchant"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Conversations retrieved successfully"
        )
    })
    public Mono<ResponseEntity<Flux<ConversationResponse>>> listConversations(
            @RequestHeader("merchant_id") String merchantId) {
        return chatAIService.listConversations(merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}


package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.BatchCardInfoRequest;
import com.hyperswitch.common.dto.CardInfoRequest;
import com.hyperswitch.common.dto.CardInfoResponse;
import com.hyperswitch.core.cardsinfo.CardInfoService;
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

import java.util.List;

/**
 * REST controller for card info operations
 */
@RestController
@RequestMapping("/api/cards")
@Tag(name = "Cards Info", description = "Card information management operations")
public class CardInfoController {
    
    private final CardInfoService cardInfoService;
    
    @Autowired
    public CardInfoController(CardInfoService cardInfoService) {
        this.cardInfoService = cardInfoService;
    }
    
    /**
     * Create card info
     * POST /api/cards/create
     */
    @PostMapping("/create")
    @Operation(
        summary = "Create card info",
        description = "Creates a new card information entry"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Card info created successfully",
            content = @Content(schema = @Schema(implementation = CardInfoResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "409", description = "Card info already exists")
    })
    public Mono<ResponseEntity<CardInfoResponse>> createCardInfo(@RequestBody CardInfoRequest request) {
        return cardInfoService.createCardInfo(request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Update card info
     * POST /api/cards/update
     */
    @PostMapping("/update")
    @Operation(
        summary = "Update card info",
        description = "Updates an existing card information entry"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Card info updated successfully",
            content = @Content(schema = @Schema(implementation = CardInfoResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Card info not found")
    })
    public Mono<ResponseEntity<CardInfoResponse>> updateCardInfo(@RequestBody CardInfoRequest request) {
        return cardInfoService.updateCardInfo(request.getCardIin(), request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Batch update card info
     * POST /api/cards/update-batch
     */
    @PostMapping("/update-batch")
    @Operation(
        summary = "Batch update card info",
        description = "Updates multiple card information entries in batch"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Card info batch updated successfully",
            content = @Content(schema = @Schema(implementation = CardInfoResponse.class))
        )
    })
    public Mono<ResponseEntity<List<CardInfoResponse>>> batchUpdateCardInfo(@RequestBody BatchCardInfoRequest request) {
        return cardInfoService.batchUpdateCardInfo(request)
            .collectList()
            .map(ResponseEntity::ok);
    }
    
    /**
     * Get card IIN info
     * GET /api/cards/{bin}
     */
    @GetMapping("/{bin}")
    @Operation(
        summary = "Get card IIN info",
        description = "Retrieves card information by IIN (Issuer Identification Number)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Card info retrieved successfully",
            content = @Content(schema = @Schema(implementation = CardInfoResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Card info not found")
    })
    public Mono<ResponseEntity<CardInfoResponse>> getCardInfo(@PathVariable("bin") String bin) {
        return cardInfoService.getCardInfo(bin)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}


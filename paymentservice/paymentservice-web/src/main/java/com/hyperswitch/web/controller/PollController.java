package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.PollResponse;
import com.hyperswitch.core.poll.PollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * REST controller for poll operations
 */
@RestController
@RequestMapping("/api/poll")
@Tag(name = "Poll", description = "Poll status operations")
public class PollController {
    
    private final PollService pollService;
    
    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }
    
    /**
     * Retrieve poll status
     * GET /api/poll/status/{poll_id}
     */
    @GetMapping("/status/{poll_id}")
    @Operation(
        summary = "Retrieve poll status",
        description = "Retrieves the status of a poll operation"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Poll status retrieved successfully",
            content = @Content(schema = @Schema(implementation = PollResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Poll not found")
    })
    public Mono<ResponseEntity<PollResponse>> getPollStatus(
            @PathVariable("poll_id") String pollId,
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return pollService.getPollStatus(merchantId, pollId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}


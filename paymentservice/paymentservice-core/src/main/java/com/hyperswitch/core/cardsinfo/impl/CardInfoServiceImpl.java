package com.hyperswitch.core.cardsinfo.impl;

import com.hyperswitch.common.dto.BatchCardInfoRequest;
import com.hyperswitch.common.dto.CardInfoRequest;
import com.hyperswitch.common.dto.CardInfoResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.cardsinfo.CardInfoService;
import com.hyperswitch.storage.entity.CardInfoEntity;
import com.hyperswitch.storage.repository.CardInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * Implementation of CardInfoService
 */
@Service
public class CardInfoServiceImpl implements CardInfoService {
    
    private static final Logger log = LoggerFactory.getLogger(CardInfoServiceImpl.class);
    
    private final CardInfoRepository cardInfoRepository;
    
    @Autowired
    public CardInfoServiceImpl(CardInfoRepository cardInfoRepository) {
        this.cardInfoRepository = cardInfoRepository;
    }
    
    @Override
    public Mono<Result<CardInfoResponse, PaymentError>> createCardInfo(CardInfoRequest request) {
        log.info("Creating card info for IIN: {}", request.getCardIin());
        
        return cardInfoRepository.findByCardIin(request.getCardIin())
            .flatMap(existing -> {
                log.warn("Card info with IIN {} already exists", request.getCardIin());
                return Mono.just(Result.<CardInfoResponse, PaymentError>err(
                    PaymentError.of("CARD_INFO_ALREADY_EXISTS",
                        "Card info with IIN " + request.getCardIin() + " already exists")
                ));
            })
            .switchIfEmpty(Mono.fromCallable(() -> {
                CardInfoEntity entity = new CardInfoEntity();
                entity.setCardIin(request.getCardIin());
                entity.setCardIssuer(request.getCardIssuer());
                entity.setCardNetwork(request.getCardNetwork());
                entity.setCardType(request.getCardType());
                entity.setCardSubtype(request.getCardSubtype());
                entity.setCardIssuingCountry(request.getCardIssuingCountry());
                entity.setBankCodeId(request.getBankCodeId());
                entity.setBankCode(request.getBankCode());
                entity.setCountryCode(request.getCountryCode());
                entity.setDateCreated(Instant.now());
                entity.setLastUpdatedProvider(request.getLastUpdatedProvider());
                return entity;
            })
            .flatMap(cardInfoRepository::save)
            .map(this::toCardInfoResponse)
            .map(Result::<CardInfoResponse, PaymentError>ok))
            .onErrorResume(error -> {
                log.error("Error creating card info", error);
                return Mono.just(Result.<CardInfoResponse, PaymentError>err(
                    PaymentError.of("CARD_INFO_CREATE_FAILED",
                        "Failed to create card info: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<CardInfoResponse, PaymentError>> updateCardInfo(String cardIin, CardInfoRequest request) {
        log.info("Updating card info for IIN: {}", cardIin);
        
        return cardInfoRepository.findByCardIin(cardIin)
            .switchIfEmpty(Mono.error(new RuntimeException("Card info not found")))
            .flatMap(entity -> {
                if (request.getCardIssuer() != null) {
                    entity.setCardIssuer(request.getCardIssuer());
                }
                if (request.getCardNetwork() != null) {
                    entity.setCardNetwork(request.getCardNetwork());
                }
                if (request.getCardType() != null) {
                    entity.setCardType(request.getCardType());
                }
                if (request.getCardSubtype() != null) {
                    entity.setCardSubtype(request.getCardSubtype());
                }
                if (request.getCardIssuingCountry() != null) {
                    entity.setCardIssuingCountry(request.getCardIssuingCountry());
                }
                if (request.getBankCodeId() != null) {
                    entity.setBankCodeId(request.getBankCodeId());
                }
                if (request.getBankCode() != null) {
                    entity.setBankCode(request.getBankCode());
                }
                if (request.getCountryCode() != null) {
                    entity.setCountryCode(request.getCountryCode());
                }
                if (request.getLastUpdatedProvider() != null) {
                    entity.setLastUpdatedProvider(request.getLastUpdatedProvider());
                }
                entity.setLastUpdated(Instant.now());
                
                return cardInfoRepository.save(entity)
                    .map(this::toCardInfoResponse)
                    .map(Result::<CardInfoResponse, PaymentError>ok);
            })
            .onErrorResume(error -> {
                log.error("Error updating card info", error);
                if (error.getMessage() != null && error.getMessage().contains("not found")) {
                    return Mono.just(Result.<CardInfoResponse, PaymentError>err(
                        PaymentError.of("CARD_INFO_NOT_FOUND",
                            "Card info with IIN " + cardIin + " not found")
                    ));
                }
                return Mono.just(Result.<CardInfoResponse, PaymentError>err(
                    PaymentError.of("CARD_INFO_UPDATE_FAILED",
                        "Failed to update card info: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Flux<CardInfoResponse> batchUpdateCardInfo(BatchCardInfoRequest request) {
        log.info("Batch updating card info for {} cards", request.getCards().size());
        
        return Flux.fromIterable(request.getCards())
            .flatMap(cardRequest -> {
                return cardInfoRepository.findByCardIin(cardRequest.getCardIin())
                    .flatMap(existing -> {
                        // Update existing
                        if (cardRequest.getCardIssuer() != null) {
                            existing.setCardIssuer(cardRequest.getCardIssuer());
                        }
                        if (cardRequest.getCardNetwork() != null) {
                            existing.setCardNetwork(cardRequest.getCardNetwork());
                        }
                        if (cardRequest.getCardType() != null) {
                            existing.setCardType(cardRequest.getCardType());
                        }
                        if (cardRequest.getCardSubtype() != null) {
                            existing.setCardSubtype(cardRequest.getCardSubtype());
                        }
                        if (cardRequest.getCardIssuingCountry() != null) {
                            existing.setCardIssuingCountry(cardRequest.getCardIssuingCountry());
                        }
                        if (cardRequest.getBankCodeId() != null) {
                            existing.setBankCodeId(cardRequest.getBankCodeId());
                        }
                        if (cardRequest.getBankCode() != null) {
                            existing.setBankCode(cardRequest.getBankCode());
                        }
                        if (cardRequest.getCountryCode() != null) {
                            existing.setCountryCode(cardRequest.getCountryCode());
                        }
                        if (cardRequest.getLastUpdatedProvider() != null) {
                            existing.setLastUpdatedProvider(cardRequest.getLastUpdatedProvider());
                        }
                        existing.setLastUpdated(Instant.now());
                        return cardInfoRepository.save(existing);
                    })
                    .switchIfEmpty(Mono.fromCallable(() -> {
                        // Create new
                        CardInfoEntity entity = new CardInfoEntity();
                        entity.setCardIin(cardRequest.getCardIin());
                        entity.setCardIssuer(cardRequest.getCardIssuer());
                        entity.setCardNetwork(cardRequest.getCardNetwork());
                        entity.setCardType(cardRequest.getCardType());
                        entity.setCardSubtype(cardRequest.getCardSubtype());
                        entity.setCardIssuingCountry(cardRequest.getCardIssuingCountry());
                        entity.setBankCodeId(cardRequest.getBankCodeId());
                        entity.setBankCode(cardRequest.getBankCode());
                        entity.setCountryCode(cardRequest.getCountryCode());
                        entity.setDateCreated(Instant.now());
                        entity.setLastUpdatedProvider(cardRequest.getLastUpdatedProvider());
                        return entity;
                    })
                    .flatMap(cardInfoRepository::save));
            })
            .map(this::toCardInfoResponse)
            .onErrorResume(error -> {
                log.error("Error in batch update card info", error);
                return Flux.empty();
            });
    }
    
    @Override
    public Mono<Result<CardInfoResponse, PaymentError>> getCardInfo(String cardIin) {
        log.info("Getting card info for IIN: {}", cardIin);
        
        return cardInfoRepository.findByCardIin(cardIin)
            .map(this::toCardInfoResponse)
            .map(Result::<CardInfoResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<CardInfoResponse, PaymentError>err(
                PaymentError.of("CARD_INFO_NOT_FOUND",
                    "Card info with IIN " + cardIin + " not found")
            )))
            .onErrorResume(error -> {
                log.error("Error getting card info", error);
                return Mono.just(Result.<CardInfoResponse, PaymentError>err(
                    PaymentError.of("CARD_INFO_RETRIEVAL_FAILED",
                        "Failed to get card info: " + error.getMessage())
                ));
            });
    }
    
    /**
     * Convert CardInfoEntity to CardInfoResponse
     */
    private CardInfoResponse toCardInfoResponse(CardInfoEntity entity) {
        CardInfoResponse response = new CardInfoResponse();
        response.setCardIin(entity.getCardIin());
        response.setCardIssuer(entity.getCardIssuer());
        response.setCardNetwork(entity.getCardNetwork());
        response.setCardType(entity.getCardType());
        response.setCardSubtype(entity.getCardSubtype());
        response.setCardIssuingCountry(entity.getCardIssuingCountry());
        response.setBankCodeId(entity.getBankCodeId());
        response.setBankCode(entity.getBankCode());
        response.setCountryCode(entity.getCountryCode());
        response.setDateCreated(entity.getDateCreated());
        response.setLastUpdated(entity.getLastUpdated());
        response.setLastUpdatedProvider(entity.getLastUpdatedProvider());
        return response;
    }
}


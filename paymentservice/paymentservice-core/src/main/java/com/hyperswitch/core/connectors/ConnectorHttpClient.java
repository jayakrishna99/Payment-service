package com.hyperswitch.core.connectors;

import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

/**
 * HTTP client for making API calls to payment connectors
 * Handles authentication, request building, and response parsing
 */
@Component
public class ConnectorHttpClient {
    
    private static final Logger log = LoggerFactory.getLogger(ConnectorHttpClient.class);
    private final WebClient webClient;
    
    public ConnectorHttpClient() {
        this.webClient = WebClient.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10MB
            .build();
    }
    
    /**
     * Execute a POST request to a connector API
     */
    public Mono<Result<Map<String, Object>, PaymentError>> post(
            String url,
            Map<String, Object> requestBody,
            Map<String, String> headers,
            String connectorName) {
        log.debug("Making POST request to connector: {}, URL: {}", connectorName, url);
        
        WebClient.RequestBodySpec requestSpec = webClient.post()
            .uri(url)
            .contentType(MediaType.APPLICATION_JSON);
        
        // Add headers
        if (headers != null) {
            headers.forEach(requestSpec::header);
        }
        
        ParameterizedTypeReference<Map<String, Object>> typeRef = 
            new ParameterizedTypeReference<Map<String, Object>>() {};
        
        return requestSpec
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(typeRef)
            .timeout(Duration.ofSeconds(30))
            .map(response -> {
                log.debug("Connector response received: {}", response);
                return Result.<Map<String, Object>, PaymentError>ok(response);
            })
            .onErrorResume(error -> {
                log.error("Error calling connector API: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("CONNECTOR_API_CALL_FAILED",
                    "Failed to call connector API: " + error.getMessage())));
            });
    }
    
    /**
     * Execute a GET request to a connector API
     */
    public Mono<Result<Map<String, Object>, PaymentError>> get(
            String url,
            Map<String, String> headers,
            String connectorName) {
        log.debug("Making GET request to connector: {}, URL: {}", connectorName, url);
        
        WebClient.RequestHeadersSpec<?> requestSpec = webClient.get()
            .uri(url);
        
        // Add headers
        if (headers != null) {
            headers.forEach(requestSpec::header);
        }
        
        ParameterizedTypeReference<Map<String, Object>> typeRef = 
            new ParameterizedTypeReference<Map<String, Object>>() {};
        
        return requestSpec
            .retrieve()
            .bodyToMono(typeRef)
            .timeout(Duration.ofSeconds(30))
            .map(response -> {
                log.debug("Connector response received: {}", response);
                return Result.<Map<String, Object>, PaymentError>ok(response);
            })
            .onErrorResume(error -> {
                log.error("Error calling connector API: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("CONNECTOR_API_CALL_FAILED",
                    "Failed to call connector API: " + error.getMessage())));
            });
    }
    
    /**
     * Build authentication headers for a connector
     */
    public Map<String, String> buildAuthHeaders(String connectorName, Map<String, String> credentials) {
        Map<String, String> headers = new java.util.HashMap<>();
        
        // Connector-specific authentication header building
        switch (connectorName.toLowerCase()) {
            case "stripe":
                // Stripe uses Bearer token authentication
                if (credentials.containsKey("api_key")) {
                    headers.put(HttpHeaders.AUTHORIZATION, "Bearer " + credentials.get("api_key"));
                }
                break;
            case "paypal":
                // PayPal uses Basic auth or Bearer token
                if (credentials.containsKey("access_token")) {
                    headers.put(HttpHeaders.AUTHORIZATION, "Bearer " + credentials.get("access_token"));
                } else if (credentials.containsKey("client_id") && credentials.containsKey("client_secret")) {
                    String auth = credentials.get("client_id") + ":" + credentials.get("client_secret");
                    String encoded = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
                    headers.put(HttpHeaders.AUTHORIZATION, "Basic " + encoded);
                }
                break;
            default:
                // Generic API key authentication
                if (credentials.containsKey("api_key")) {
                    headers.put(HttpHeaders.AUTHORIZATION, "Bearer " + credentials.get("api_key"));
                } else if (credentials.containsKey("api_key_header")) {
                    headers.put(credentials.get("api_key_header"), credentials.get("api_key"));
                }
                break;
        }
        
        return headers;
    }
    
    /**
     * Get connector API base URL
     */
    public String getConnectorBaseUrl(String connectorName) {
        // Connector-specific base URLs
        switch (connectorName.toLowerCase()) {
            case "stripe":
                return "https://api.stripe.com/v1";
            case "paypal":
                return "https://api-m.paypal.com";
            case "razorpay":
                return "https://api.razorpay.com/v1";
            case "adyen":
                return "https://pal-test.adyen.com/pal/servlet";
            default:
                // Default or custom connector URL
                return System.getenv("CONNECTOR_" + connectorName.toUpperCase() + "_BASE_URL");
        }
    }
}


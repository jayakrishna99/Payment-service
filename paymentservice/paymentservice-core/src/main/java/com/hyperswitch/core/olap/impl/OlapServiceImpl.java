package com.hyperswitch.core.olap.impl;

import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.olap.OlapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

/**
 * Implementation of OLAP service using ClickHouse
 */
@Service
public class OlapServiceImpl implements OlapService {
    
    private static final Logger log = LoggerFactory.getLogger(OlapServiceImpl.class);
    
    private final WebClient webClient;
    private final String databaseName;
    private final boolean enabled;
    
    public OlapServiceImpl(
            @Value("${analytics.clickhouse.host:http://localhost:8123}") String host,
            @Value("${analytics.clickhouse.username:default}") String username,
            @Value("${analytics.clickhouse.password:}") String password,
            @Value("${analytics.clickhouse.database:default}") String databaseName,
            @Value("${analytics.clickhouse.enabled:false}") boolean enabled) {
        this.databaseName = databaseName;
        this.enabled = enabled;
        
        // Build WebClient with basic auth
        WebClient.Builder builder = WebClient.builder()
            .baseUrl(host);
        
        if (!username.isEmpty() && !password.isEmpty()) {
            builder.defaultHeaders(headers -> {
                String auth = Base64.getEncoder().encodeToString(
                    (username + ":" + password).getBytes());
                headers.set("Authorization", "Basic " + auth);
            });
        }
        
        this.webClient = builder.build();
    }
    
    @Override
    public Mono<Result<List<Map<String, Object>>, PaymentError>> executeQuery(String query) {
        if (!enabled) {
            log.warn("OLAP is not enabled, returning empty result");
            return Mono.just(Result.ok(Collections.emptyList()));
        }
        
        log.debug("Executing ClickHouse query: {}", query);
        
        // Build query parameters
        Map<String, String> params = new HashMap<>();
        params.put("database", databaseName);
        params.put("date_time_output_format", "iso");
        params.put("output_format_json_quote_64bit_integers", "0");
        
        // Append FORMAT JSON to query
        String formattedQuery = query + "\nFORMAT JSON";
        
        return webClient.post()
            .uri(uriBuilder -> {
                var builder = uriBuilder.queryParam("database", databaseName)
                    .queryParam("date_time_output_format", "iso")
                    .queryParam("output_format_json_quote_64bit_integers", "0");
                return builder.build();
            })
            .bodyValue(formattedQuery)
            .retrieve()
            .onStatus(status -> status.isError(), response -> {
                return response.bodyToMono(String.class)
                    .flatMap(errorBody -> {
                        log.error("ClickHouse query failed: {}", errorBody);
                        return Mono.error(new RuntimeException("ClickHouse query failed: " + errorBody));
                    });
            })
            .bodyToMono(ClickHouseResponse.class)
            .map(response -> {
                if (response.getData() != null) {
                    return Result.<List<Map<String, Object>>, PaymentError>ok(response.getData());
                } else {
                    return Result.<List<Map<String, Object>>, PaymentError>ok(Collections.emptyList());
                }
            })
            .onErrorResume(error -> {
                log.error("Error executing ClickHouse query: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("OLAP_QUERY_FAILED",
                    "Failed to execute OLAP query: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<Boolean, PaymentError>> healthCheck() {
        if (!enabled) {
            return Mono.just(Result.ok(false));
        }
        
        return executeQuery("SELECT 1")
            .map(result -> {
                if (result.isOk()) {
                    return Result.<Boolean, PaymentError>ok(true);
                } else {
                    return Result.<Boolean, PaymentError>ok(false);
                }
            })
            .onErrorResume(error -> {
                log.error("ClickHouse health check failed: {}", error.getMessage(), error);
                return Mono.just(Result.ok(false));
            });
    }
    
    @Override
    public String getDatabaseName() {
        return databaseName;
    }
    
    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    /**
     * ClickHouse response wrapper
     * ClickHouse returns: {"data": [...], "rows": N, "statistics": {...}}
     */
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
    private static class ClickHouseResponse {
        private List<Map<String, Object>> data;
        private Integer rows;
        
        public List<Map<String, Object>> getData() {
            return data != null ? data : Collections.emptyList();
        }
        
        public void setData(List<Map<String, Object>> data) {
            this.data = data;
        }
        
        public Integer getRows() {
            return rows;
        }
        
        public void setRows(Integer rows) {
            this.rows = rows;
        }
    }
}


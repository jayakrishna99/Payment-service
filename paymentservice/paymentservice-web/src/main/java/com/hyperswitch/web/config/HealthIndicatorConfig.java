package com.hyperswitch.web.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Custom health indicators for database and Redis
 */
@Component
public class HealthIndicatorConfig {
    
    private HealthIndicatorConfig() {
        // Utility class - prevent instantiation
    }

    /**
     * Database health indicator
     */
    @Component
    public static class DatabaseHealthIndicator implements ReactiveHealthIndicator {
        
        private final ConnectionFactory connectionFactory;

        public DatabaseHealthIndicator(ConnectionFactory connectionFactory) {
            this.connectionFactory = connectionFactory;
        }

        @Override
        public Mono<Health> health() {
            return Mono.from(connectionFactory.create())
                .flatMap(connection -> Mono.from(connection.createStatement("SELECT 1").execute())
                    .then(Mono.just(Health.up().withDetail("database", "PostgreSQL").build())))
                .onErrorResume(error -> Mono.just(
                    Health.down().withDetail("database", "PostgreSQL")
                        .withDetail("error", error.getMessage()).build()
                ));
        }
    }

    /**
     * Redis health indicator
     */
    @Component
    public static class RedisHealthIndicator implements ReactiveHealthIndicator {
        
        private final ReactiveRedisConnectionFactory redisConnectionFactory;

        public RedisHealthIndicator(ReactiveRedisConnectionFactory redisConnectionFactory) {
            this.redisConnectionFactory = redisConnectionFactory;
        }

        @Override
        public Mono<Health> health() {
            return redisConnectionFactory.getReactiveConnection()
                .ping()
                .then(Mono.just(Health.up().withDetail("redis", "Connected").build()))
                .onErrorResume(error -> Mono.just(
                    Health.down().withDetail("redis", "Disconnected")
                        .withDetail("error", error.getMessage()).build()
                ));
        }
    }
}


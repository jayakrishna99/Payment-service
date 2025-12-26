package com.hyperswitch.storage.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * Database configuration for R2DBC
 */
@Configuration
@EnableR2dbcRepositories(basePackages = "com.hyperswitch.storage.repository")
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Value("${spring.r2dbc.url}")
    private String url;

    @Value("${spring.r2dbc.username}")
    private String username;

    @Value("${spring.r2dbc.password}")
    private String password;

    @Override
    @Bean
    @SuppressWarnings("null")
    public ConnectionFactory connectionFactory() {
        // Parse URL: r2dbc:postgresql://localhost:5432/hyperswitch_db
        String[] parts = url.replace("r2dbc:postgresql://", "").split("/");
        String[] hostPort = parts[0].split(":");
        String host = hostPort[0];
        int port = hostPort.length > 1 ? Integer.parseInt(hostPort[1]) : 5432;
        String database = parts.length > 1 ? parts[1] : "hyperswitch_db";

        return new PostgresqlConnectionFactory(
            PostgresqlConnectionConfiguration.builder()
                .host(host)
                .port(port)
                .database(database)
                .username(username)
                .password(password)
                .build()
        );
    }
}


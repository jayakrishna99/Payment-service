package com.hyperswitch.connectors.config;

import com.hyperswitch.connectors.impl.StripeConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for connector beans
 */
@Configuration
public class ConnectorConfig {

    @Bean
    public StripeConnector stripeConnector() {
        return new StripeConnector();
    }
}


package com.hyperswitch.connectors.config;

import com.hyperswitch.connectors.ConnectorInterface;
import com.hyperswitch.connectors.impl.ConnectorServiceImpl;
import com.hyperswitch.connectors.impl.StripeConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration for connector beans
 */
@Configuration
public class ConnectorConfig {

    @Bean
    public ConnectorServiceImpl connectorService(List<ConnectorInterface> connectors) {
        return new ConnectorServiceImpl(connectors);
    }

    @Bean
    public StripeConnector stripeConnector() {
        return new StripeConnector();
    }
}


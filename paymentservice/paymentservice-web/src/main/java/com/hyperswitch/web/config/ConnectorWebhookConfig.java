package com.hyperswitch.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration for connector webhook secrets
 */
@Configuration
@ConfigurationProperties(prefix = "hyperswitch.connectors")
public class ConnectorWebhookConfig {

    private Map<String, WebhookConfig> webhooks = new HashMap<>();

    public Map<String, WebhookConfig> getWebhooks() {
        return webhooks;
    }

    public void setWebhooks(Map<String, WebhookConfig> webhooks) {
        this.webhooks = webhooks;
    }

    public String getWebhookSecret(String connector) {
        WebhookConfig config = webhooks.get(connector);
        return config != null ? config.getSecret() : "";
    }

    public static class WebhookConfig {
        private String secret;

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }
}


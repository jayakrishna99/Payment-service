package com.hyperswitch.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * OpenAPI/Swagger configuration
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hyperswitchOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Hyperswitch Payment Service API")
                .description("Open-source payment switch implementation in Java Spring Boot. " +
                    "This API provides payment processing capabilities including payment creation, " +
                    "confirmation, capture, refunds, mandates, payment links, and more.\n\n" +
                    "## Error Codes\n\n" +
                    "The API uses standard error codes for different error scenarios:\n\n" +
                    "### Hard Decline Errors (Non-retryable)\n" +
                    "- `INSUFFICIENT_FUNDS` - Card has insufficient funds\n" +
                    "- `CARD_DECLINED` - Card was declined by the issuer\n" +
                    "- `EXPIRED_CARD` - Card has expired\n" +
                    "- `INVALID_CVC` - Invalid CVC code provided\n" +
                    "- `INVALID_CARD_NUMBER` - Invalid card number\n" +
                    "- `CARD_NOT_SUPPORTED` - Card type not supported\n" +
                    "- `FRAUDULENT` - Transaction flagged as fraudulent\n\n" +
                    "### Soft Decline Errors (Retryable)\n" +
                    "- `PROCESSING_ERROR` - Temporary processing error\n" +
                    "- `TIMEOUT` - Request timeout\n" +
                    "- `NETWORK_ERROR` - Network connectivity issue\n" +
                    "- `SERVICE_UNAVAILABLE` - Service temporarily unavailable\n\n" +
                    "### Authentication Errors\n" +
                    "- `AUTHENTICATION_REQUIRED` - 3DS authentication required\n" +
                    "- `REQUIRES_3DS` - 3DS challenge required\n" +
                    "- `CHALLENGE_REQUIRED` - Additional authentication challenge needed\n\n" +
                    "### General Errors\n" +
                    "- `NOT_FOUND` - Resource not found\n" +
                    "- `VALIDATION_ERROR` - Request validation failed\n" +
                    "- `INTERNAL_SERVER_ERROR` - Internal server error")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Hyperswitch Team")
                    .url("https://hyperswitch.io")
                    .email("support@hyperswitch.io"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
            .servers(List.of(
                new Server()
                    .url("http://localhost:8080")
                    .description("Local development server"),
                new Server()
                    .url("https://api.hyperswitch.io")
                    .description("Production server")
            ))
            .tags(List.of(
                new Tag().name("Payments").description("Payment processing operations"),
                new Tag().name("Customers").description("Customer management"),
                new Tag().name("Payment Methods").description("Payment method management"),
                new Tag().name("Refunds").description("Refund operations"),
                new Tag().name("Mandates").description("Recurring payment mandates"),
                new Tag().name("Subscriptions").description("Subscription management"),
                new Tag().name("Payment Links").description("Payment link generation"),
                new Tag().name("Webhooks").description("Webhook management")
            ))
            .components(new io.swagger.v3.oas.models.Components()
                .addExamples("errorInsufficientFunds", new Example()
                    .summary("Insufficient Funds Error")
                    .description("Error response when card has insufficient funds")
                    .value(Map.of(
                        "error", Map.of(
                            "code", "INSUFFICIENT_FUNDS",
                            "message", "Insufficient funds in the account",
                            "connectorError", "Your card has insufficient funds."
                        )
                    )))
                .addExamples("errorCardDeclined", new Example()
                    .summary("Card Declined Error")
                    .description("Error response when card is declined")
                    .value(Map.of(
                        "error", Map.of(
                            "code", "CARD_DECLINED",
                            "message", "Card was declined by the issuer",
                            "connectorError", "Your card was declined."
                        )
                    )))
                .addExamples("errorNotFound", new Example()
                    .summary("Not Found Error")
                    .description("Error response when resource is not found")
                    .value(Map.of(
                        "error", Map.of(
                            "code", "NOT_FOUND",
                            "message", "Payment not found"
                        )
                    )))
                .addExamples("errorValidation", new Example()
                    .summary("Validation Error")
                    .description("Error response when request validation fails")
                    .value(Map.of(
                        "error", Map.of(
                            "code", "VALIDATION_ERROR",
                            "message", "Invalid request parameters: amount is required"
                        )
                    )))
            );
    }
}


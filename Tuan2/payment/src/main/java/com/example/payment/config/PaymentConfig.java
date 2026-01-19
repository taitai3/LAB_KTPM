package com.example.payment.config;

import org.springframework.stereotype.Component;

@Component
public class PaymentConfig {

    private final String apiKey = "API_KEY_SAMPLE";
    private final String secretKey = "SECRET_KEY_SAMPLE";

    public String getApiKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}

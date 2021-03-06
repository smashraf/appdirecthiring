package com.appdirect.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {


    @Value("${consumer.key}")
    private String consumerKey;

    @Value("${consumer.secret}")
    private String secret;

    @Bean
    public ClientSecret appDirectClient() {
        return new ClientSecret(consumerKey, secret);
    }


}

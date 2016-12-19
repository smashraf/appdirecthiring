package com.appdirect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by covacsis on 16/12/16.
 */
@Configuration
public class PropertyConfig {
    static @Bean
    public PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Properties to support the 'test' mode of operation.
     */
    @Configuration
    @Profile("test")
    @PropertySource(ignoreResourceNotFound = true,
            value={"classpath:common.properties","classpath:application-test.properties"})
    static class Test {
    }

    /**
     * Properties to support the 'test' mode of operation.
     */
    @Configuration
    @Profile({ "dev", "default" })
    @PropertySource(ignoreResourceNotFound = true,value = {"classpath:common.properties", "classpath:application.properties",})
    static class Dev {
    }

    /**
     * Properties to support the 'production' mode of operation.
     */
    @Configuration
    @Profile("production")
    @PropertySource({"classpath:common.properties","classpath:application-production.properties"})
    static class Production {
        // Define additional beans for this profile here
    }
}

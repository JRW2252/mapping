package com.mapping.mapping.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ApplicationConfiguration {
    @Value("${message}")
    private String message;

    @Bean
    public String message() {
        log.info("MESSAGE" + message);
        return message;
    }

}

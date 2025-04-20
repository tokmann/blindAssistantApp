package ru.truehack.backend.assistant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServerConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

package com.example.creditapp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue creditDecisionsQueue() {
        return new Queue("credit-decisions", true);
    }
}
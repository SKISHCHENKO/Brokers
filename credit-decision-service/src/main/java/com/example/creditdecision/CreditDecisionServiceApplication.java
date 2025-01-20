package com.example.creditdecision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.creditapp.model"})
public class CreditDecisionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreditDecisionServiceApplication.class, args);
    }
}
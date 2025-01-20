package com.example.creditapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "credit_applications")
public class CreditApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private Integer termMonths;
    private BigDecimal monthlyIncome;

    @Enumerated(EnumType.STRING)
    private CreditStatus status;

    private LocalDateTime createdAt = LocalDateTime.now();
}
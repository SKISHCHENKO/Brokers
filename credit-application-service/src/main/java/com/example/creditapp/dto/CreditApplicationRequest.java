package com.example.creditapp.dto;

import com.example.creditapp.model.CreditStatus;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreditApplicationRequest {
    private BigDecimal amount;
    private Integer termMonths;
    private BigDecimal monthlyIncome;
    private CreditStatus status;
}
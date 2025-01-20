package com.example.creditapp.event;

import com.example.creditapp.model.CreditStatus;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreditApplicationEvent {
    private Long applicationId;
    private BigDecimal amount;
    private Integer termMonths;
    private BigDecimal monthlyIncome;
    private CreditStatus status;
}
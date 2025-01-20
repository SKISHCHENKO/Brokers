package com.example.creditdecision.service;



import com.example.creditapp.event.CreditApplicationEvent;
import com.example.creditapp.event.CreditDecisionEvent;


import com.example.creditapp.model.CreditStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CreditDecisionService {
    private final RabbitTemplate rabbitTemplate;

    public CreditDecisionService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void processApplication(CreditApplicationEvent event) {
        boolean approved = isApplicationApproved(event);
        CreditStatus status = isApplicationApproved(event) ? CreditStatus.APPROVED : CreditStatus.REJECTED;

        CreditDecisionEvent decisionEvent = new CreditDecisionEvent();
        decisionEvent.setApplicationId(event.getApplicationId());
        decisionEvent.setApproved(approved);
        decisionEvent.setStatus(status);

        // Отправка решения о кредите в RabbitMQ
        rabbitTemplate.convertAndSend("credit-decisions", decisionEvent);
    }

    private boolean isApplicationApproved(CreditApplicationEvent event) {
        // Проверяем, что срок кредита больше нуля, чтобы избежать деления на ноль
        if (event.getTermMonths() <= 0) {
            throw new IllegalArgumentException("Term months must be greater than zero.");
        }

        // Рассчитываем общую сумму платежей
        BigDecimal monthlyPayment = event.getAmount().divide(
                BigDecimal.valueOf(event.getTermMonths()), // делим сумму на количество месяцев
                2, // количество знаков после запятой
                RoundingMode.HALF_UP // режим округления
        );

        // Проверяем, не превышает ли сумма платежей 50% от дохода
        return monthlyPayment .compareTo(event.getMonthlyIncome().multiply(BigDecimal.valueOf(0.5))) <= 0;
    }
}


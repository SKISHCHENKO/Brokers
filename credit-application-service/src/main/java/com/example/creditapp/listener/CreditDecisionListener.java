package com.example.creditapp.listener;

import com.example.creditapp.event.CreditDecisionEvent;
import com.example.creditapp.service.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditDecisionListener {
    private final CreditApplicationService service;

    @RabbitListener(queues = "credit-decisions")
    public void handleCreditDecision(CreditDecisionEvent event) {
        service.processDecision(event);
    }
}
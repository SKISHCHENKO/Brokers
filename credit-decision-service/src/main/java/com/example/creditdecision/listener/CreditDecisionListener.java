package com.example.creditdecision.listener;


import com.example.creditapp.event.CreditApplicationEvent;
import com.example.creditdecision.service.CreditDecisionService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CreditDecisionListener {

    private final CreditDecisionService creditDecisionService;
    private final RabbitTemplate rabbitTemplate;

    public CreditDecisionListener(CreditDecisionService creditDecisionService, RabbitTemplate rabbitTemplate) {
        this.creditDecisionService = creditDecisionService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @KafkaListener(topics = "credit-applications", groupId = "credit-decision-group")
    public void listen(CreditApplicationEvent applicationEvent) {
        if (applicationEvent == null) {
            // Логирование ошибки
            return;
        }

        // Обработка заявки
        creditDecisionService.processApplication(applicationEvent);

    }
}

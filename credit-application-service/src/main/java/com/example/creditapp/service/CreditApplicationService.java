package com.example.creditapp.service;

import com.example.creditapp.dto.CreditApplicationRequest;
import com.example.creditapp.event.CreditApplicationEvent;
import com.example.creditapp.event.CreditDecisionEvent;
import com.example.creditapp.model.CreditApplication;
import com.example.creditapp.model.CreditStatus;
import com.example.creditapp.repository.CreditApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreditApplicationService {
    private final CreditApplicationRepository repository;
    private final KafkaTemplate<String, CreditApplicationEvent> kafkaTemplate;

    @Transactional
    public Long submitApplication(CreditApplicationRequest request) {
        CreditApplication application = new CreditApplication();
        application.setAmount(request.getAmount());
        application.setTermMonths(request.getTermMonths());
        application.setMonthlyIncome(request.getMonthlyIncome());
        application.setStatus(CreditStatus.PROCESSING);
        
        application = repository.save(application);

        CreditApplicationEvent event = new CreditApplicationEvent();
        event.setApplicationId(application.getId());
        event.setAmount(application.getAmount());
        event.setTermMonths(application.getTermMonths());
        event.setMonthlyIncome(application.getMonthlyIncome());
        event.setStatus(CreditStatus.PROCESSING);

        kafkaTemplate.send("credit-applications", event);

        return application.getId();
    }

    @Transactional
    public void processDecision(CreditDecisionEvent event) {
        repository.findById(event.getApplicationId())
                .ifPresent(application -> {
                    application.setStatus(event.isApproved() ? CreditStatus.APPROVED : CreditStatus.REJECTED);
                    repository.save(application);
                });
    }

    public CreditApplication getApplication(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
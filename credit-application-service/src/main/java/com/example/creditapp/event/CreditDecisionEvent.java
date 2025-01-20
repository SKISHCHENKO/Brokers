package com.example.creditapp.event;

import com.example.creditapp.model.CreditStatus;
import lombok.Data;

@Data
public class CreditDecisionEvent {
    private Long applicationId;
    private boolean approved;
    private CreditStatus status;
}
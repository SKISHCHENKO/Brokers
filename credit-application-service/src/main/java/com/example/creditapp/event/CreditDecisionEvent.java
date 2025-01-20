package com.example.creditapp.event;

import com.example.creditapp.model.CreditStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreditDecisionEvent implements Serializable {
    private Long applicationId;
    private boolean approved;
    private CreditStatus status;
}
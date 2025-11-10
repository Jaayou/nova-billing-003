package com.nova.billing.core.service;

import com.nova.billing.core.model.Bill;
import com.nova.billing.core.model.CalculationParameter;

public interface CalculationService {

    Bill calculate(CalculationParameter param);
}
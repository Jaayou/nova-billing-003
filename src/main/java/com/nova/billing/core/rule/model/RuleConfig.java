package com.nova.billing.core.rule.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RuleConfig {
    private String ruleId;
    private String domain;
    private String ruleType;

    private String factor1;
    private String condition1;

    private String formulaId;
    private String actionId;
}

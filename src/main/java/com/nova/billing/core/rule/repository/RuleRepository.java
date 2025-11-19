package com.nova.billing.core.rule.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.nova.billing.core.rule.model.RuleConfig;

import jakarta.annotation.PostConstruct;

@Component
public class RuleRepository {

    private final Map<String, RuleConfig> ruleMap = new HashMap<>();

    @PostConstruct
    public void init() {
        System.out.println("  [RBS Init] Loading Billing Rules into in-memory repository...");

        RuleConfig rule1 = RuleConfig.builder()
                .ruleId("R_W_001")
                .domain("WIRELESS")
                .ruleType("MONTHLY_FEE_BASIC")
                .factor1("VIP")
                .condition1("=")
                .formulaId("PRORATE_01")
                .actionId("APPLY_CHARGE")
                .build();

        ruleMap.put(rule1.getDomain() + "_" + rule1.getRuleType(), rule1);

        RuleConfig rule2 = RuleConfig.builder()
                .ruleId("R_WI_001")
                .domain("WIRED")
                .ruleType("MONTHLY_FEE_BASIC")
                .factor1("GENERAL")
                .condition1("=")
                .formulaId("PRORATE_01")
                .actionId("APPLY_CHARGE")
                .build();
        ruleMap.put(rule2.getDomain() + "_" + rule2.getRuleType(), rule2);
    }

    public RuleConfig findRule(String domain, String ruleType) {
        String key = domain.toUpperCase() + "_" + ruleType.toUpperCase();

        RuleConfig config = ruleMap.get(key);

        if (config == null) {
            System.err.println("  [RBS ERROR] No rule found for key: " + key);
        }
        return config;
    }
}

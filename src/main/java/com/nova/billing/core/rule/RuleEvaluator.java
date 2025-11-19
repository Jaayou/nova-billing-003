package com.nova.billing.core.rule;

import org.springframework.stereotype.Service;

import com.nova.billing.core.executor.ProrateFormulaExecutor;
import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.DomainType;
import com.nova.billing.core.rule.model.RuleConfig;
import com.nova.billing.core.rule.repository.RuleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RuleEvaluator {

    private final RuleRepository ruleRepository;
    private final ProrateFormulaExecutor formulaExecutor;

    public void evaluateAndExucute(BillingContext context) {

        String customerGrade = context.getParam().getCustomer().getGrade();
        DomainType domainType = context.getParam().getDomainType();

        RuleConfig rule = ruleRepository.findRule(domainType.toString(), "MONTHLY_FEE_BASIC");

        System.out.println("  [### customerGrade]" + customerGrade);
        System.out.println("  [### rule]" + rule.toString());
        if (rule != null && rule.getFactor1().equals(customerGrade)) {
            System.out.println("  [RBS] Rule matched: " + rule.getRuleId() + ". Executing formula: " + rule.getFormulaId());

            context.getProducts().forEach(product -> {
                if (product.getProductType().equals("PLAN")) {
                    formulaExecutor.executeProrateFormula(context, product, rule.getFormulaId());
                }
            });
        }
    }
}

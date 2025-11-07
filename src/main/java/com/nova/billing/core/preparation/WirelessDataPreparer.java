package com.nova.billing.core.preparation;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.domain.BillingSubject;
import com.nova.billing.domain.CalculationParameter;
import com.nova.billing.domain.DomainType;
import com.nova.billing.domain.SubContract;

@Component
public class WirelessDataPreparer implements DataPreparer {

    @Override
    public DomainType getDomainType() {
        return DomainType.WIRELESS;
    }

    @Override
    public BillingSubject prepareData(CalculationParameter param) {
        System.out.println("    [Preparer] -> (2) Calling 'WirelessDataPreparer'...");

        List<SubContract> subContracts;

        if (param.getServiceId().equals("SVC_WL_001")) {
            subContracts = List.of(
                    SubContract.builder()
                            .subContractId("WL_sub_001")
                            .productType("WL_STANDARD_PLAN")
                            .build(),
                    SubContract.builder()
                            .subContractId("WL_sub_002")
                            .productType("WL_LITE_PLAN")
                            .build());
        } else if (param.getServiceId().equals("SVC_WL_005")) {
            subContracts = List.of(
                    SubContract.builder()
                            .subContractId(param.getServiceId() + "_sub")
                            .productType(param.getProductType())
                            .build());
        } else {
            subContracts = List.of(
                    SubContract.builder()
                            .subContractId(param.getServiceId() + "_sub")
                            .productType(param.getProductType())
                            .build());
        }

        System.out.println(
                "    [Preparer] -> BillingSubject Built. Found " + subContracts.size() + " sub-contracts.");

        return BillingSubject.builder()
                .subContracts(subContracts)
                .build();
    }
}

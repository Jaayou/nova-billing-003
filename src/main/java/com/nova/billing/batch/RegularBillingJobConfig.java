package com.nova.billing.batch;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.nova.billing.core.CalculationService;
import com.nova.billing.domain.Bill;
import com.nova.billing.domain.CalculationParameter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RegularBillingJobConfig {

    private final CalculationService calculationService;

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private static final int CHUNK_SIZE = 10;

    @Bean
    public Job regularBillingJob() {
        return new JobBuilder("regularBillingJob", jobRepository)
                .start(regularBillingStep())
                .build();
    }

    @Bean
    public Step regularBillingStep() {
        return new StepBuilder("regualrBillingStep", jobRepository)
                .<CalculationParameter, Bill>chunk(CHUNK_SIZE, transactionManager)
                .reader(billingItemReader())
                .processor(billingItemProcessor())
                .writer(billingItemWriter())
                .build();
    }

    @Bean
    public ItemReader<CalculationParameter> billingItemReader() {
        System.out.println("\n[Batch Reader] Creating mock data list...");

        List<CalculationParameter> targetList = List.of(
                CalculationParameter.builder().serviceId("SVC_WL_001").isHotbill(false).build(),
                CalculationParameter.builder().serviceId("SVC_WD_002").isHotbill(false).build(),
                CalculationParameter.builder().serviceId("SVC_WL_005").isHotbill(false).build());

        return new ListItemReader<>(targetList);
    }

    @Bean
    public ItemProcessor<CalculationParameter, Bill> billingItemProcessor() {
        return param -> {
            System.out.println("\n[Batch Processor] Calling Engine for Rep ID: " + param.getServiceId());

            Bill calculatedBill = calculationService.calculate(param);

            return calculatedBill;
        };
    }

    @Bean
    public ItemWriter<Bill> billingItemWriter() {
        return chunk -> {
            System.out.println("  [Batch Writer] Writing " + chunk.getItems().size() + " Bills (Transaction Commit)");

            for (Bill bill : chunk.getItems()) {
                System.out.println("    [v0.13 Batch Writer] -> Rep ID: " + bill.getServiceId()
                        + ", Final Amount: " + bill.getTotalAmount());
            }
        };
    }
}

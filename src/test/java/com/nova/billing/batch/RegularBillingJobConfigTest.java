package com.nova.billing.batch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.context.ApplicationContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBatchTest
@SpringBootTest
@Sql(scripts = "classpath:org/springframework/batch/core/schema-h2.sql", 
     executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RegularBillingJobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    //@Autowired
    //private ApplicationContext applicationContext;

    @Autowired
    @Qualifier("regularBillingJob")
    private Job regularBillingJob;

    @AfterEach
    public void teanDown() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    @DisplayName("Regular Batch Job")
    void testRegularBillingJob() throws Exception {
        //
        //Job regularBillingJob = (Job) applicationContext.getBean("regularBillingJob");
        this.jobLauncherTestUtils.setJob(regularBillingJob);

        JobParameters jobParameters = jobLauncherTestUtils.getUniqueJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

        jobExecution.getStepExecutions().forEach(stepExecution -> {
            assertEquals(4, stepExecution.getReadCount());
            assertEquals(4, stepExecution.getWriteCount());
        });
    }
}

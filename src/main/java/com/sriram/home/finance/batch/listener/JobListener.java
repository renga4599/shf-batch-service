package com.sriram.home.finance.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("******************************");
        log.info("Before job - listener " + jobExecution.getJobInstance().getJobName());
        log.info("Before job - listener " + jobExecution.getJobParameters());
        log.info("Before job - listener " + jobExecution.getExecutionContext());
        jobExecution.getExecutionContext().put("newKey", "12");
        log.info("******************************");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("******************************");
        log.info("After job - listener " + jobExecution.getJobInstance().getInstanceId());
        log.info("After job - listener " + jobExecution.getJobInstance().getJobName());
        log.info("After job - listener " + jobExecution.getJobParameters());
        log.info("After job - listener " + jobExecution.getExecutionContext());
        log.info("******************************");
    }
}

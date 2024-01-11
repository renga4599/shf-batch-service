package com.sriram.home.finance.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("******************************");
        log.info("Before STEP - step name " + stepExecution.getStepName());
        log.info("Before STEP context " + stepExecution.getExecutionContext());
        log.info("Before STEP - job context " + stepExecution.getJobExecution().getExecutionContext());
        log.info("******************************");
        stepExecution.getExecutionContext().put("secKey", "secVal1");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("******************************");
        log.info("After  STEP - step name " + stepExecution.getStepName());
        log.info("After  STEP - step context " + stepExecution.getExecutionContext());
        log.info("After  STEP - job context " + stepExecution.getJobExecution().getExecutionContext());
        log.info("******************************");
        return null;
    }
}

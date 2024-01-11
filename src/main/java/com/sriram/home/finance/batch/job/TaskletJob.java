package com.sriram.home.finance.batch.job;

import com.sriram.home.finance.batch.listener.JobListener;
import com.sriram.home.finance.batch.listener.StepListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Slf4j
@Configuration
public class TaskletJob {

    private final JobListener jobListener;
    private final StepListener stepListener;
    private final DataSourceTransactionManager batchTransactionManager;

    @Autowired
    public TaskletJob(JobListener jobListener, StepListener stepListener, DataSourceTransactionManager batchTransactionManager) {
        this.jobListener = jobListener;
        this.stepListener = stepListener;
        this.batchTransactionManager = batchTransactionManager;
    }


    @Bean
    public Job firstTaskletJob(JobRepository jobRepository) {
        log.info("This is my first job");
        return new JobBuilder("myTaskletJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(firstStep(jobRepository))
                .next(secondStep(jobRepository))
                .listener(jobListener)
                .build();
    }

    private Step firstStep(JobRepository jobRepository){
        log.info("This is my first step");
        return new StepBuilder("myStep", jobRepository)
                .tasklet(firstTask(), batchTransactionManager)
                .listener(stepListener)
                .build();
    }

    private Step secondStep(JobRepository jobRepository){
        log.info("This is my second step");
        return new StepBuilder("myStep2", jobRepository)
                .tasklet(secondTask(), batchTransactionManager)
                .build();
    }

    private Tasklet firstTask(){
        return (contribution, chunkContext) -> {
            log.info("This is my first tasklet with context in step2" + chunkContext.getStepContext().getJobExecutionContext().get("newKey"));
            return RepeatStatus.FINISHED;
        };
    }

    private Tasklet secondTask(){
        return (contribution, chunkContext) -> RepeatStatus.FINISHED;
    }
}

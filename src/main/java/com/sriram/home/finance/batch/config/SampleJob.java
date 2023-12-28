package com.sriram.home.finance.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class SampleJob {
    @Autowired
    private DataSource batchDataSource;

    @Bean
    public DataSourceTransactionManager transactionManager() {
        System.out.println("in transaction manager renga");
        return new DataSourceTransactionManager(batchDataSource);
    }

    @Bean
    public Job job(JobRepository jobRepository) {
        System.out.println("This is my first job");
        return new JobBuilder("myFirstJob", jobRepository)
                .start(firstStep(jobRepository))
                .next(secondStep(jobRepository))
                .build();
    }

    private Step firstStep(JobRepository jobRepository){
        System.out.println("This is my first step");
        return new StepBuilder("myStep", jobRepository)
                .tasklet(firstTask(), transactionManager())
                .build();
    }

    private Step secondStep(JobRepository jobRepository){
        System.out.println("This is my second step");
        return new StepBuilder("myStep2", jobRepository)
                .tasklet(firstTask(), transactionManager())
                .build();
    }

    private Tasklet firstTask(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("This is my first tasklet");
                return RepeatStatus.FINISHED;
            }
        };
    }

    private Tasklet secondTask(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("This is my second tasklet");
                return RepeatStatus.FINISHED;
            }
        };
    }
}

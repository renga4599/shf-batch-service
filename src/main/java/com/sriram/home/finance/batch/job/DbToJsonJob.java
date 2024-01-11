package com.sriram.home.finance.batch.job;

import com.sriram.home.finance.batch.constants.JobConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.sriram.home.finance.batch.constants.JobConstants.DB_TO_JSON_STUDENT_JOB;

@Slf4j
@Configuration
public class DbToJsonJob {

    private final Step dbToJsonStudentStep;

    @Autowired
    public DbToJsonJob(Step dbToJsonStudentStep) {
        this.dbToJsonStudentStep = dbToJsonStudentStep;
    }

    @Bean
    public Job dbToJsonStudentJob(JobRepository jobRepository) {
        log.info("Start configuring job {} ", JobConstants.DB_TO_JSON_STUDENT_JOB);
        return new JobBuilder(DB_TO_JSON_STUDENT_JOB, jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(dbToJsonStudentStep)
                .build();
    }


}

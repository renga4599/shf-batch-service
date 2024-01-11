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

import static com.sriram.home.finance.batch.constants.JobConstants.JSON_TO_DB_STUDENT_JOB;

@Slf4j
@Configuration
public class JsonToDbJob {

    private final Step jsonToDbStudentStep;

    @Autowired
    public JsonToDbJob(Step jsonToDbStudentStep) {
        this.jsonToDbStudentStep = jsonToDbStudentStep;
    }

    @Bean
    public Job jsonToDbStudentJob(JobRepository jobRepository) {
        log.info("Start configuring job {} ", JobConstants.JSON_TO_DB_STUDENT_JOB);
        return new JobBuilder(JSON_TO_DB_STUDENT_JOB, jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(jsonToDbStudentStep)
                .build();
    }
}

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

import static com.sriram.home.finance.batch.constants.JobConstants.XML_TO_DB_STUDENT_JOB;

@Slf4j
@Configuration
public class XmlToDbJob {
    private final Step xmlToDbStudentStep;

    @Autowired
    public XmlToDbJob(Step xmlToDbStudentStep) {
        this.xmlToDbStudentStep = xmlToDbStudentStep;
    }

    @Bean
    public Job xmlToDbStudentJob(JobRepository jobRepository) {
        log.info("Start configuring job {} ", JobConstants.XML_TO_DB_STUDENT_JOB);
        return new JobBuilder(XML_TO_DB_STUDENT_JOB, jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(xmlToDbStudentStep)
                .build();
    }
}

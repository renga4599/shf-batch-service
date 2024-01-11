package com.sriram.home.finance.batch.step;

import com.sriram.home.finance.batch.listener.StepListener;
import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.model.StudentJson;
import com.sriram.home.finance.batch.processor.JsonToJdbcStudentProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class JsonToDbStep {

    private final JobRepository jobRepository;
    private final DataSourceTransactionManager batchTransactionManager;
    private final JsonItemReader<StudentJson> studentJsonItemReader;
    private final JsonToJdbcStudentProcessor jsonToJdbcStudentProcessor;
    private final JdbcBatchItemWriter<StudentJdbc> studentJdbcItemWriter;
    private final StepListener stepListener;

    @Autowired
    public JsonToDbStep(JobRepository jobRepository,
                        DataSourceTransactionManager batchTransactionManager,
                        JsonItemReader<StudentJson> studentJsonItemReader,
                        JsonToJdbcStudentProcessor jsonToJdbcStudentProcessor,
                        JdbcBatchItemWriter<StudentJdbc> studentJdbcItemWriter,
                        StepListener stepListener) {
        this.jobRepository = jobRepository;
        this.batchTransactionManager = batchTransactionManager;
        this.studentJsonItemReader = studentJsonItemReader;
        this.jsonToJdbcStudentProcessor = jsonToJdbcStudentProcessor;
        this.studentJdbcItemWriter = studentJdbcItemWriter;
        this.stepListener = stepListener;
    }

    @Bean
    public Step jsonToDbStudentStep(){
        return new StepBuilder("JSON-DB-STEP", jobRepository)
                .<StudentJson, StudentJdbc>chunk(3, batchTransactionManager)
                .reader(studentJsonItemReader)
                .processor(jsonToJdbcStudentProcessor)
                .writer(studentJdbcItemWriter)
                .listener(stepListener)
                .build();
    }
}

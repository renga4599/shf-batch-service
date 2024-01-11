package com.sriram.home.finance.batch.step;

import com.sriram.home.finance.batch.listener.StepListener;
import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.model.StudentJson;
import com.sriram.home.finance.batch.processor.JdbcToJsonStudentProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class DbToJsonStep {

    private final JobRepository jobRepository;
    private final DataSourceTransactionManager batchTransactionManager;
    private final StepListener stepListener;
    private final JdbcCursorItemReader<StudentJdbc> studentJdbcItemReader;
    private final JdbcToJsonStudentProcessor jdbcToJsonStudentProcessor;
    private final JsonFileItemWriter<StudentJson> studentJsonItemWriter;

    @Autowired
    public DbToJsonStep(JobRepository jobRepository,
                        DataSourceTransactionManager batchTransactionManager,
                        StepListener stepListener,
                        JdbcCursorItemReader<StudentJdbc> studentJdbcItemReader,
                        JdbcToJsonStudentProcessor jdbcToJsonStudentProcessor,
                        JsonFileItemWriter<StudentJson> studentJsonItemWriter) {
        this.jobRepository = jobRepository;
        this.batchTransactionManager = batchTransactionManager;
        this.stepListener = stepListener;
        this.studentJdbcItemReader = studentJdbcItemReader;
        this.jdbcToJsonStudentProcessor = jdbcToJsonStudentProcessor;
        this.studentJsonItemWriter = studentJsonItemWriter;
    }


    @Bean
    public Step dbToJsonStudentStep(){
        return new StepBuilder("DB-JSON-STEP", jobRepository)
                .<StudentJdbc, StudentJson>chunk(3, batchTransactionManager)
                .reader(studentJdbcItemReader)
                .processor(jdbcToJsonStudentProcessor)
                .writer(studentJsonItemWriter)
                .listener(stepListener)
                .build();
    }
}

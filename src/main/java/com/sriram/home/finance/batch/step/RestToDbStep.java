package com.sriram.home.finance.batch.step;

import com.sriram.home.finance.batch.listener.StepListener;
import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.model.StudentRest;
import com.sriram.home.finance.batch.processor.RestToJdbcStudentProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class RestToDbStep {

    private final JobRepository jobRepository;
    private final DataSourceTransactionManager batchTransactionManager;
    private final ItemReaderAdapter<StudentRest> studentRestItemReaderAdapter;
    private final RestToJdbcStudentProcessor restToJdbcStudentProcessor;
    private final JdbcBatchItemWriter<StudentJdbc> studentJdbcItemWriter;
    private final StepListener stepListener;

    public RestToDbStep(JobRepository jobRepository,
                        DataSourceTransactionManager batchTransactionManager,
                        ItemReaderAdapter<StudentRest> studentRestItemReaderAdapter,
                        RestToJdbcStudentProcessor restToJdbcStudentProcessor,
                        JdbcBatchItemWriter<StudentJdbc> studentJdbcItemWriter,
                        StepListener stepListener) {
        this.jobRepository = jobRepository;
        this.batchTransactionManager = batchTransactionManager;
        this.studentRestItemReaderAdapter = studentRestItemReaderAdapter;
        this.restToJdbcStudentProcessor = restToJdbcStudentProcessor;
        this.studentJdbcItemWriter = studentJdbcItemWriter;
        this.stepListener = stepListener;
    }

    @Bean
    public Step restToDbStudentStep(){
        return new StepBuilder("REST-DB-STEP", jobRepository)
                .<StudentRest, StudentJdbc>chunk(3, batchTransactionManager)
                .reader(studentRestItemReaderAdapter)
                .processor(restToJdbcStudentProcessor)
                .writer(studentJdbcItemWriter)
                .listener(stepListener)
                .build();
    }
}

package com.sriram.home.finance.batch.step;

import com.sriram.home.finance.batch.listener.StepListener;
import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.model.StudentRest;
import com.sriram.home.finance.batch.processor.JdbcToRestStudentProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class DbToRestStep {

    private final JobRepository jobRepository;
    private final DataSourceTransactionManager batchTransactionManager;
    private final StepListener stepListener;
    private final JdbcCursorItemReader<StudentJdbc> studentJdbcItemReader;
    private final JdbcToRestStudentProcessor jdbcToRestStudentProcessor;
    private final ItemWriterAdapter<StudentRest> studentRestItemWriter;

    @Autowired
    public DbToRestStep(JobRepository jobRepository,
                        DataSourceTransactionManager batchTransactionManager,
                        StepListener stepListener,
                        JdbcCursorItemReader<StudentJdbc> studentJdbcItemReader,
                        JdbcToRestStudentProcessor jdbcToRestStudentProcessor,
                        ItemWriterAdapter<StudentRest> studentRestItemWriter) {
        this.jobRepository = jobRepository;
        this.batchTransactionManager = batchTransactionManager;
        this.stepListener = stepListener;
        this.studentJdbcItemReader = studentJdbcItemReader;
        this.jdbcToRestStudentProcessor = jdbcToRestStudentProcessor;
        this.studentRestItemWriter = studentRestItemWriter;
    }


    @Bean
    public Step dbToRestStudentStep(){
        return new StepBuilder("DB-REST-STEP", jobRepository)
                .<StudentJdbc, StudentRest>chunk(3, batchTransactionManager)
                .reader(studentJdbcItemReader)
                .processor(jdbcToRestStudentProcessor)
                .writer(studentRestItemWriter)
                .listener(stepListener)
                .build();
    }
}

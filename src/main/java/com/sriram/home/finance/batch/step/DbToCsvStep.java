package com.sriram.home.finance.batch.step;

import com.sriram.home.finance.batch.listener.StepListener;
import com.sriram.home.finance.batch.model.StudentCsv;
import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.processor.JdbcToCsvStudentProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class DbToCsvStep {

    private final JobRepository jobRepository;
    private final DataSourceTransactionManager batchTransactionManager;
    private final StepListener stepListener;
    private final JdbcCursorItemReader<StudentJdbc> studentJdbcItemReader;
    private final JdbcToCsvStudentProcessor jdbcToCsvStudentProcessor;
    private final FlatFileItemWriter<StudentCsv> studentCsvItemWriter;

    @Autowired
    public DbToCsvStep(JobRepository jobRepository,
                       DataSourceTransactionManager batchTransactionManager,
                       StepListener stepListener,
                       JdbcCursorItemReader<StudentJdbc> studentJdbcItemReader,
                       JdbcToCsvStudentProcessor jdbcToCsvStudentProcessor,
                       FlatFileItemWriter<StudentCsv> studentCsvItemWriter) {
        this.jobRepository = jobRepository;
        this.batchTransactionManager = batchTransactionManager;
        this.stepListener = stepListener;
        this.studentJdbcItemReader = studentJdbcItemReader;
        this.jdbcToCsvStudentProcessor = jdbcToCsvStudentProcessor;
        this.studentCsvItemWriter = studentCsvItemWriter;
    }


    @Bean
    public Step dbToCsvStudentStep(){
        return new StepBuilder("DB-CSV-STEP", jobRepository)
                .<StudentJdbc, StudentCsv>chunk(3, batchTransactionManager)
                .reader(studentJdbcItemReader)
                .processor(jdbcToCsvStudentProcessor)
                .writer(studentCsvItemWriter)
                .listener(stepListener)
                .build();
    }
}

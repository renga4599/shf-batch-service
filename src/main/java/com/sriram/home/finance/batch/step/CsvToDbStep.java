package com.sriram.home.finance.batch.step;

import com.sriram.home.finance.batch.listener.StepListener;
import com.sriram.home.finance.batch.model.StudentCsv;
import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.processor.CsvToJdbcStudentProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class CsvToDbStep {

    private final JobRepository jobRepository;
    private final DataSourceTransactionManager batchTransactionManager;
    private final FlatFileItemReader<StudentCsv> studentCsvFileItemReader;
    private final CsvToJdbcStudentProcessor csvToJdbcStudentProcessor;
    private final JdbcBatchItemWriter<StudentJdbc> studentJdbcItemWriter;
    private final StepListener stepListener;

    @Autowired
    public CsvToDbStep(JobRepository jobRepository,
                       DataSourceTransactionManager batchTransactionManager,
                       FlatFileItemReader<StudentCsv> studentCsvFileItemReader,
                       CsvToJdbcStudentProcessor csvToJdbcStudentProcessor,
                       JdbcBatchItemWriter<StudentJdbc> studentJdbcItemWriter,
                       StepListener stepListener) {
        this.jobRepository = jobRepository;
        this.batchTransactionManager = batchTransactionManager;
        this.studentCsvFileItemReader = studentCsvFileItemReader;
        this.csvToJdbcStudentProcessor = csvToJdbcStudentProcessor;
        this.studentJdbcItemWriter = studentJdbcItemWriter;
        this.stepListener = stepListener;
    }

    @Bean
    public Step csvToDbStudentStep(){
        return new StepBuilder("CSV-DB-STEP", jobRepository)
                .<StudentCsv, StudentJdbc>chunk(3, batchTransactionManager)
                .reader(studentCsvFileItemReader)
                .processor(csvToJdbcStudentProcessor)
                .writer(studentJdbcItemWriter)
                .listener(stepListener)
                .build();
    }
}

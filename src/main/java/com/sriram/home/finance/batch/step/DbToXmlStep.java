package com.sriram.home.finance.batch.step;

import com.sriram.home.finance.batch.listener.StepListener;
import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.model.StudentXml;
import com.sriram.home.finance.batch.processor.JdbcToXmlStudentProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class DbToXmlStep {

    private final JobRepository jobRepository;
    private final DataSourceTransactionManager batchTransactionManager;
    private final StepListener stepListener;
    private final JdbcCursorItemReader<StudentJdbc> studentJdbcItemReader;
    private final JdbcToXmlStudentProcessor jdbcToXmlStudentProcessor;
    private final StaxEventItemWriter<StudentXml> studentXmlItemWriter;

    @Autowired
    public DbToXmlStep(JobRepository jobRepository,
                       DataSourceTransactionManager batchTransactionManager,
                       StepListener stepListener,
                       JdbcCursorItemReader<StudentJdbc> studentJdbcItemReader,
                       JdbcToXmlStudentProcessor jdbcToXmlStudentProcessor,
                       StaxEventItemWriter<StudentXml> studentXmlItemWriter) {
        this.jobRepository = jobRepository;
        this.batchTransactionManager = batchTransactionManager;
        this.stepListener = stepListener;
        this.studentJdbcItemReader = studentJdbcItemReader;
        this.jdbcToXmlStudentProcessor = jdbcToXmlStudentProcessor;
        this.studentXmlItemWriter = studentXmlItemWriter;
    }


    @Bean
    public Step dbToXmlStudentStep(){
        return new StepBuilder("DB-XML-STEP", jobRepository)
                .<StudentJdbc, StudentXml>chunk(3, batchTransactionManager)
                .reader(studentJdbcItemReader)
                .processor(jdbcToXmlStudentProcessor)
                .writer(studentXmlItemWriter)
                .listener(stepListener)
                .build();
    }
}

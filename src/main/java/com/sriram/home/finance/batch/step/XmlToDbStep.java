package com.sriram.home.finance.batch.step;

import com.sriram.home.finance.batch.listener.StepListener;
import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.model.StudentXml;
import com.sriram.home.finance.batch.processor.XmlToJdbcStudentProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class XmlToDbStep {

    private final JobRepository jobRepository;
    private final DataSourceTransactionManager batchTransactionManager;
    private final StaxEventItemReader<StudentXml> studentXmlStaxEventItemReader;
    private final XmlToJdbcStudentProcessor xmlToJdbcStudentProcessor;
    private final JdbcBatchItemWriter<StudentJdbc> studentJdbcItemWriter;
    private final StepListener stepListener;

    @Autowired
    public XmlToDbStep(JobRepository jobRepository,
                       DataSourceTransactionManager batchTransactionManager,
                       StaxEventItemReader<StudentXml> studentXmlStaxEventItemReader,
                       XmlToJdbcStudentProcessor xmlToJdbcStudentProcessor,
                       JdbcBatchItemWriter<StudentJdbc> studentJdbcItemWriter,
                       StepListener stepListener) {
        this.jobRepository = jobRepository;
        this.batchTransactionManager = batchTransactionManager;
        this.studentXmlStaxEventItemReader = studentXmlStaxEventItemReader;
        this.xmlToJdbcStudentProcessor = xmlToJdbcStudentProcessor;
        this.studentJdbcItemWriter = studentJdbcItemWriter;
        this.stepListener = stepListener;
    }


    @Bean
    public Step xmlToDbStudentStep(){
        return new StepBuilder("XML-DB-STEP", jobRepository)
                .<StudentXml, StudentJdbc>chunk(3, batchTransactionManager)
                .reader(studentXmlStaxEventItemReader)
                .processor(xmlToJdbcStudentProcessor)
                .writer(studentJdbcItemWriter)
                .listener(stepListener)
                .build();
    }
}

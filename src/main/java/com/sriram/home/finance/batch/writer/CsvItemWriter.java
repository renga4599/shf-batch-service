package com.sriram.home.finance.batch.writer;

import com.sriram.home.finance.batch.model.StudentCsv;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.time.LocalDateTime;

@Configuration
public class CsvItemWriter {

    @Bean
    @StepScope
    public FlatFileItemWriter<StudentCsv> studentCsvItemWriter(@Value("#{jobParameters['outputCsvFileLocation']}") FileSystemResource fileSystemResource){
        FlatFileItemWriter<StudentCsv> studentCsvItemWriter = new FlatFileItemWriter<>();
        studentCsvItemWriter.setResource(fileSystemResource);
        studentCsvItemWriter.setHeaderCallback(writer -> writer.write("Id,First Name, Last Name, Email"));
        studentCsvItemWriter.setLineAggregator(getDelimitedLineAggregator());
        studentCsvItemWriter.setFooterCallback(writer -> writer.write("Created at " + LocalDateTime.now()));
        return studentCsvItemWriter;

    }

    private DelimitedLineAggregator<StudentCsv> getDelimitedLineAggregator(){
        DelimitedLineAggregator<StudentCsv> delimitedLineAggregator = new DelimitedLineAggregator<>();
        delimitedLineAggregator.setFieldExtractor(getStudentJdbcBeanWrapperFieldExtractor());
        delimitedLineAggregator.setDelimiter("|");
        return delimitedLineAggregator;
    }

    private BeanWrapperFieldExtractor<StudentCsv> getStudentJdbcBeanWrapperFieldExtractor(){
        BeanWrapperFieldExtractor<StudentCsv> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
        beanWrapperFieldExtractor.setNames(new String[] {"id", "firstName", "lastName", "email"});
        return beanWrapperFieldExtractor;
    }
}

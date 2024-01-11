package com.sriram.home.finance.batch.reader;

import com.sriram.home.finance.batch.model.StudentCsv;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class CsvItemReader {

    @StepScope
    @Bean
    public FlatFileItemReader<StudentCsv> studentCsvFileItemReader(@Value("#{jobParameters['inputCsvFileLocation']}") FileSystemResource fileSystemResource){
        FlatFileItemReader<StudentCsv> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setLineMapper(defaultLineMapper());
        flatFileItemReader.setLinesToSkip(1);
//        flatFileItemReader.setResource(resourceLoader.getResource("classpath:static/students.csv"));
        flatFileItemReader.setResource(fileSystemResource);
        return flatFileItemReader;
    }

    private DefaultLineMapper<StudentCsv> defaultLineMapper(){
        DefaultLineMapper<StudentCsv> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(getDelimitedLineTokenizer());
        defaultLineMapper.setFieldSetMapper(getStudentCsvBeanWrapperFieldSetMapper());
        return defaultLineMapper;

    }

    private BeanWrapperFieldSetMapper<StudentCsv> getStudentCsvBeanWrapperFieldSetMapper() {
        BeanWrapperFieldSetMapper<StudentCsv> studentCsvBeanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        studentCsvBeanWrapperFieldSetMapper.setTargetType(StudentCsv.class);
        return studentCsvBeanWrapperFieldSetMapper;
    }

    private DelimitedLineTokenizer getDelimitedLineTokenizer() {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames("ID","First Name","Last Name","Email");
        return delimitedLineTokenizer;
    }
}

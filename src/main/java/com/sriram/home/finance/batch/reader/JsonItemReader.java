package com.sriram.home.finance.batch.reader;

import com.sriram.home.finance.batch.model.StudentJson;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class JsonItemReader {

    @StepScope
    @Bean
    public org.springframework.batch.item.json.JsonItemReader<StudentJson> studentJsonItemReader(@Value("#{jobParameters['inputJsonFileLocation']}") FileSystemResource fileSystemResource){
        org.springframework.batch.item.json.JsonItemReader<StudentJson> jsonItemReader = new org.springframework.batch.item.json.JsonItemReader<>();
        jsonItemReader.setResource(fileSystemResource);
        jsonItemReader.setJsonObjectReader(new JacksonJsonObjectReader<>(StudentJson.class));
        return jsonItemReader;
    }
}

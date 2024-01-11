package com.sriram.home.finance.batch.writer;

import com.sriram.home.finance.batch.model.StudentJson;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class JsonItemWriter {

    @Bean
    @StepScope
    public JsonFileItemWriter<StudentJson> studentJsonItemWriter(@Value("#{jobParameters['outputJsonFileLocation']}") FileSystemResource fileSystemResource){
        return new JsonFileItemWriter<>(fileSystemResource, new JacksonJsonObjectMarshaller<>());
    }
}

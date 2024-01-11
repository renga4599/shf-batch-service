package com.sriram.home.finance.batch.reader;

import com.sriram.home.finance.batch.model.StudentRest;
import com.sriram.home.finance.batch.service.RestItemReaderService;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestItemReader {
    private final RestItemReaderService restItemReaderService;

    @Autowired
    public RestItemReader(RestItemReaderService restItemReaderService) {
        this.restItemReaderService = restItemReaderService;
    }

    @StepScope
    @Bean
    public ItemReaderAdapter<StudentRest> studentRestItemReaderAdapter(){
        ItemReaderAdapter<StudentRest> jsonItemReader = new ItemReaderAdapter<>();
        jsonItemReader.setTargetObject(restItemReaderService);
        jsonItemReader.setTargetMethod("getStudent");
        jsonItemReader.setArguments(new Object[]{1L, "Renga"});
        return jsonItemReader;
    }

}

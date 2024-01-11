package com.sriram.home.finance.batch.writer;

import com.sriram.home.finance.batch.model.StudentRest;
import com.sriram.home.finance.batch.service.StudentService;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RestItemWriter {

    @Autowired
    StudentService studentService;

    @Bean
    @StepScope
    public ItemWriterAdapter<StudentRest> studentRestItemWriter(){
        ItemWriterAdapter<StudentRest> itemWriterAdapter = new ItemWriterAdapter<>();
        itemWriterAdapter.setTargetObject(studentService);
        itemWriterAdapter.setTargetMethod("addStudent");
        // With item writer you do not need to pass the model, as it is taken from processor
        //itemWriterAdapter.setArguments(new Object[]{1L, "Renga"});
        return itemWriterAdapter;
    }

}

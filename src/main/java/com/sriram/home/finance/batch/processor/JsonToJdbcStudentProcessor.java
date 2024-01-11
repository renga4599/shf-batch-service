package com.sriram.home.finance.batch.processor;

import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.model.StudentJson;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonToJdbcStudentProcessor implements ItemProcessor<StudentJson, StudentJdbc> {
    @Override
    public StudentJdbc process(StudentJson item) {
        return new StudentJdbc(item.getId(), item.getFirstName(), item.getLastName(), item.getEmail());
    }
}

package com.sriram.home.finance.batch.processor;

import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.model.StudentRest;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestToJdbcStudentProcessor implements ItemProcessor<StudentRest, StudentJdbc> {
    @Override
    public StudentJdbc process(StudentRest item) {
        return new StudentJdbc(item.getId(), item.getFirstName(), item.getLastName(), item.getEmail());
    }
}

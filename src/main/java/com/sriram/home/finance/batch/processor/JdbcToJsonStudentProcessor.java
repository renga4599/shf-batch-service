package com.sriram.home.finance.batch.processor;

import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.model.StudentJson;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcToJsonStudentProcessor implements ItemProcessor<StudentJdbc, StudentJson> {
    @Override
    public StudentJson process(StudentJdbc item) {
        return new StudentJson(item.getId(), item.getFirstName(), item.getLastName(), item.getEmail());
    }
}

package com.sriram.home.finance.batch.processor;

import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.model.StudentRest;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcToRestStudentProcessor implements ItemProcessor<StudentJdbc, StudentRest> {
    @Override
    public StudentRest process(StudentJdbc item) {
        return new StudentRest(item.getId(), item.getFirstName(), item.getLastName(), item.getEmail());
    }
}

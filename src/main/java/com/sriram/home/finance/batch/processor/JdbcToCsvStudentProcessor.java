package com.sriram.home.finance.batch.processor;

import com.sriram.home.finance.batch.model.StudentCsv;
import com.sriram.home.finance.batch.model.StudentJdbc;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcToCsvStudentProcessor implements ItemProcessor<StudentJdbc, StudentCsv> {
    @Override
    public StudentCsv process(StudentJdbc item) {
        return new StudentCsv(item.getId(), item.getFirstName(), item.getLastName(), item.getEmail());
    }
}

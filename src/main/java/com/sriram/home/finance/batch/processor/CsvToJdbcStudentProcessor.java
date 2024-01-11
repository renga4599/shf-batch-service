package com.sriram.home.finance.batch.processor;

import com.sriram.home.finance.batch.model.StudentCsv;
import com.sriram.home.finance.batch.model.StudentJdbc;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvToJdbcStudentProcessor implements ItemProcessor<StudentCsv, StudentJdbc> {
    @Override
    public StudentJdbc process(StudentCsv item) {
        return new StudentJdbc(item.getId(), item.getFirstName(), item.getLastName(), item.getEmail());
    }
}

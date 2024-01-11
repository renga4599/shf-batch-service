package com.sriram.home.finance.batch.processor;

import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.model.StudentXml;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcToXmlStudentProcessor implements ItemProcessor<StudentJdbc, StudentXml> {
    @Override
    public StudentXml process(StudentJdbc item) {
        return new StudentXml(item.getId(), item.getFirstName(), item.getLastName(), item.getEmail());
    }
}

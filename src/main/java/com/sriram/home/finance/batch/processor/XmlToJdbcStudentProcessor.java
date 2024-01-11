package com.sriram.home.finance.batch.processor;

import com.sriram.home.finance.batch.model.StudentJdbc;
import com.sriram.home.finance.batch.model.StudentXml;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlToJdbcStudentProcessor implements ItemProcessor<StudentXml, StudentJdbc> {
    @Override
    public StudentJdbc process(StudentXml item) {
        return new StudentJdbc(item.getId(), item.getFirstName(), item.getLastName(), item.getEmail());
    }
}

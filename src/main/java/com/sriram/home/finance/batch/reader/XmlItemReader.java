package com.sriram.home.finance.batch.reader;

import com.sriram.home.finance.batch.model.StudentXml;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class XmlItemReader {
    private final Jaxb2Marshaller studentJaxb2Marshaller;

    @Autowired
    public XmlItemReader(Jaxb2Marshaller studentJaxb2Marshaller) {
        this.studentJaxb2Marshaller = studentJaxb2Marshaller;
    }

    @StepScope
    @Bean
    public StaxEventItemReader<StudentXml> studentXmlStaxEventItemReader(@Value("#{jobParameters['inputXmlFileLocation']}") FileSystemResource fileSystemResource){
        StaxEventItemReader<StudentXml> studentXmlStaxEventItemReader = new StaxEventItemReader<>();
        studentXmlStaxEventItemReader.setResource(fileSystemResource);
        studentXmlStaxEventItemReader.setFragmentRootElementName("student");
        studentXmlStaxEventItemReader.setUnmarshaller(studentJaxb2Marshaller);
        return studentXmlStaxEventItemReader;
    }
}

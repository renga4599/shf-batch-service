package com.sriram.home.finance.batch.writer;

import com.sriram.home.finance.batch.model.StudentXml;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class XmlItemWriter {

    private final Jaxb2Marshaller studentJaxb2Marshaller;

    @Autowired
    public XmlItemWriter(Jaxb2Marshaller studentJaxb2Marshaller) {
        this.studentJaxb2Marshaller = studentJaxb2Marshaller;
    }

    @Bean
    @StepScope
    public StaxEventItemWriter<StudentXml> studentXmlItemWriter(@Value("#{jobParameters['outputXmlFileLocation']}") FileSystemResource fileSystemResource){
        StaxEventItemWriter<StudentXml> studentXmlStaxEventItemWriter = new StaxEventItemWriter<>();
        studentXmlStaxEventItemWriter.setResource(fileSystemResource);
        studentXmlStaxEventItemWriter.setRootTagName("students");
        studentXmlStaxEventItemWriter.setMarshaller(studentJaxb2Marshaller);
        return studentXmlStaxEventItemWriter;
    }
}


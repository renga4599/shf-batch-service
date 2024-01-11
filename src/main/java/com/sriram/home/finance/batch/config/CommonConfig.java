package com.sriram.home.finance.batch.config;

import com.sriram.home.finance.batch.model.StudentXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.sql.DataSource;

@Configuration
public class CommonConfig {

    private final DataSource batchDataSource;

    @Autowired
    public CommonConfig(DataSource batchDataSource) {
        this.batchDataSource = batchDataSource;
    }

    @Bean
    public Jaxb2Marshaller studentJaxb2Marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(StudentXml.class);
        return jaxb2Marshaller;
    }

    @Bean
    public DataSourceTransactionManager batchTransactionManager() {
        return new DataSourceTransactionManager(batchDataSource);
    }
}

package com.sriram.home.finance.batch.reader;

import com.sriram.home.finance.batch.model.StudentJdbc;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcItemReader {

    private final DataSource dataSource;
    @Autowired
    public JdbcItemReader(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @StepScope
    @Bean
    public JdbcCursorItemReader<StudentJdbc> studentJdbcItemReader(){
        JdbcCursorItemReader<StudentJdbc> jdbcCursorItemReader = new JdbcCursorItemReader<>();
        jdbcCursorItemReader.setDataSource(dataSource);
        jdbcCursorItemReader.setSql("SELECT ID as id, FIRST_NAME as firstName,  LAST_NAME as lastName, EMAIL as email FROM STUDENT");
        jdbcCursorItemReader.setRowMapper(studentJdbcBeanPropertyRowMapper());
        jdbcCursorItemReader.setMaxItemCount(8); //get maximum 8 record
        jdbcCursorItemReader.setCurrentItemCount(2); //(remove first 2 from the 8) starts from 3rd record
        return jdbcCursorItemReader;
    }

    private BeanPropertyRowMapper<StudentJdbc> studentJdbcBeanPropertyRowMapper(){
        BeanPropertyRowMapper<StudentJdbc> studentJdbcBeanPropertyRowMapper = new BeanPropertyRowMapper<>();
        studentJdbcBeanPropertyRowMapper.setMappedClass(StudentJdbc.class);
        return studentJdbcBeanPropertyRowMapper;
    }
}

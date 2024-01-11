package com.sriram.home.finance.batch.writer;

import com.sriram.home.finance.batch.model.StudentJdbc;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbcItemWriter {

    @Autowired
    DataSource dataSource;

    @Bean
    public JdbcBatchItemWriter<StudentJdbc> studentJdbcItemWriter(){
        JdbcBatchItemWriter<StudentJdbc> studentJsonJdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        studentJsonJdbcBatchItemWriter.setDataSource(dataSource);
        populateSqlStatement(studentJsonJdbcBatchItemWriter);
        return studentJsonJdbcBatchItemWriter;
    }

    private static void populateSqlStatement(JdbcBatchItemWriter<StudentJdbc> studentJsonJdbcBatchItemWriter) {
//        studentJsonJdbcBatchItemWriter.setSql("insert into student (id, first_name, last_name, email) values (:id, :firstName, :lastName, :email)");
//        studentJsonJdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<StudentJson>());
//        studentJsonJdbcBatchItemWriter.setSql("insert into student (id, first_name, last_name, email) values (?,?,?,?)");
        studentJsonJdbcBatchItemWriter.setSql("insert into student (first_name, last_name, email) values (?,?,?)");
        studentJsonJdbcBatchItemWriter.setItemPreparedStatementSetter((item, ps) -> {
//            ps.setLong(1, item.getId());
            ps.setString(1, item.getFirstName());
            ps.setString(2, item.getLastName());
            ps.setString(3, item.getEmail());
        });
    }
}

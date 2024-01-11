package com.sriram.home.finance.batch.writer;

import com.sriram.home.finance.batch.model.StudentJdbc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ItemWriter implements org.springframework.batch.item.ItemWriter<StudentJdbc> {
    @Override
    public void write(Chunk<? extends StudentJdbc> chunk) {
        log.info("Inside writer...");
        chunk.getItems().forEach(System.out::println);
        log.info("*************************");
    }
}

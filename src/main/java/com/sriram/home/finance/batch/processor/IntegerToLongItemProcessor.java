package com.sriram.home.finance.batch.processor;

import org.springframework.stereotype.Component;

@Component
public class IntegerToLongItemProcessor implements org.springframework.batch.item.ItemProcessor<Integer, Long> {
// integer is the input type of the processor, and it is the output (return) type of the reader
// Long is the output (return) type of the processor, and it is the input type for the writer
    @Override
    public Long process(Integer item) {
        System.out.println("Inside processor ---- ");
        return (long) (item * 20);
    }
}

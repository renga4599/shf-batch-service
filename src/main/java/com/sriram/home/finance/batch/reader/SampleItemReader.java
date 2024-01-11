package com.sriram.home.finance.batch.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleItemReader implements ItemReader<Integer> {
    List<Integer> list = List.of(1, 2, 3, 4,5,6,7,8,9,10);
    int idx = 0;
    // class level member. So that this is can be rest to zero and can be reused.


    @Override
    public Integer read() {
        System.out.println("Inside the reader..");
        Integer item;
        if(idx < list.size()){
            item = list.get(idx);
            idx++;
            return item;
        }
        idx = 0; //resetting the index to zero so that we can read the source again.
        return null;
    }
}

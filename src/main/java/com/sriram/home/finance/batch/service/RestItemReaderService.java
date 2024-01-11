package com.sriram.home.finance.batch.service;

import com.sriram.home.finance.batch.model.StudentRest;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class RestItemReaderService {

    private final StudentService studentService;
    private List<StudentRest> studentRestList;

    @Autowired
    public RestItemReaderService(StudentService studentService) {
        this.studentService = studentService;
    }

    public StudentRest getStudent(long id, String name){
        if (Objects.isNull(studentRestList)){
            studentRestList = studentService.getAllStudents();
        }

        if(CollectionUtils.isNotEmpty(studentRestList)){
            return studentRestList.remove(0);
        }
        return null;
    }
}

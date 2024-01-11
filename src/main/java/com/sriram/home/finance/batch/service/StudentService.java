package com.sriram.home.finance.batch.service;

import com.sriram.home.finance.batch.model.StudentRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public StudentService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<StudentRest> getAllStudents(){
        StudentRest[] students = restTemplateBuilder.build().getForObject("http://localhost:8080/shf-user-service/api/v1/user", StudentRest[].class);
        if(Objects.isNull(students)){
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(students));
    }

    public StudentRest addStudent(StudentRest studentRest){
        StudentRest studentRestResponse = restTemplateBuilder.build().postForObject("http://localhost:8080/shf-user-service/api/v1/user", studentRest, StudentRest.class);
        System.out.println("Created student " + studentRestResponse);
        return studentRestResponse;
    }
}

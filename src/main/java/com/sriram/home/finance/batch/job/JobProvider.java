package com.sriram.home.finance.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.sriram.home.finance.batch.constants.JobConstants.*;

@Configuration
public class JobProvider {

    private final Job csvToDbStudentJob;
    private final Job xmlToDbStudentJob;
    private final Job jsonToDbStudentJob;
    private final Job restToDbStudentJob;
    private final Job dbToCsvStudentJob;
    private final Job dbToXmlStudentJob;
    private final Job dbToJsonStudentJob;
    private final Job dbToRestStudentJob;

    @Autowired()
    public JobProvider(Job csvToDbStudentJob,
                       Job xmlToDbStudentJob,
                       Job jsonToDbStudentJob,
                       Job restToDbStudentJob,
                       Job dbToCsvStudentJob,
                       Job dbToXmlStudentJob,
                       Job dbToJsonStudentJob,
                       Job dbToRestStudentJob) {
        this.csvToDbStudentJob = csvToDbStudentJob;
        this.xmlToDbStudentJob = xmlToDbStudentJob;
        this.jsonToDbStudentJob = jsonToDbStudentJob;
        this.restToDbStudentJob = restToDbStudentJob;
        this.dbToCsvStudentJob = dbToCsvStudentJob;
        this.dbToXmlStudentJob = dbToXmlStudentJob;
        this.dbToJsonStudentJob = dbToJsonStudentJob;
        this.dbToRestStudentJob = dbToRestStudentJob;
    }


    @Bean
    public TreeMap<String, Job> batchJobBeanMap(){
        TreeMap<String, Job> batchJobBeanMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        batchJobBeanMap.put(CSV_TO_DB_STUDENT_JOB, csvToDbStudentJob);
        batchJobBeanMap.put(XML_TO_DB_STUDENT_JOB, xmlToDbStudentJob);
        batchJobBeanMap.put(JSON_TO_DB_STUDENT_JOB, jsonToDbStudentJob);
        batchJobBeanMap.put(REST_TO_DB_STUDENT_JOB, restToDbStudentJob);
        batchJobBeanMap.put(DB_TO_CSV_STUDENT_JOB, dbToCsvStudentJob);
        batchJobBeanMap.put(DB_TO_XML_STUDENT_JOB, dbToXmlStudentJob);
        batchJobBeanMap.put(DB_TO_JSON_STUDENT_JOB, dbToJsonStudentJob);
        batchJobBeanMap.put(DB_TO_REST_STUDENT_JOB, dbToRestStudentJob);
        return batchJobBeanMap;
    }
}

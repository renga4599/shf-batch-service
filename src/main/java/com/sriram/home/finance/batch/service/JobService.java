package com.sriram.home.finance.batch.service;

import com.sriram.home.finance.batch.model.JobParamRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.naming.InvalidNameException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@Slf4j
@Component
public class JobService {

    private final JobLauncher jobLauncher;
    private final TreeMap<String, Job> batchJobBeanMap;

    @Autowired
    public JobService(JobLauncher jobLauncher, TreeMap<String, Job> batchJobBeanMap) {
        this.jobLauncher = jobLauncher;
        this.batchJobBeanMap = batchJobBeanMap;
    }

    @Async
    public void triggerJob(String jobName, List<JobParamRequest> jobParamRequestList) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, InvalidNameException {

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        if(Objects.nonNull(jobParamRequestList)){
            jobParamRequestList.forEach(jobParamRequest -> jobParametersBuilder.addJobParameter(jobParamRequest.getKey(), new JobParameter(jobParamRequest.getParamValue(), jobParamRequest.getParamClassType())));
        }

        JobParameters jobParameters = jobParametersBuilder
                .addLocalDateTime("TimesNow", LocalDateTime.now())
                .addJobParameter("myTimeParam", new JobParameter(LocalDateTime.now(), LocalDateTime.class))
                .toJobParameters();

        JobExecution jobExecution;
        if(batchJobBeanMap.containsKey(jobName)){
            jobExecution = jobLauncher.run(batchJobBeanMap.get(jobName), jobParameters);
            log.info("Job execution id " + jobExecution.getId() + " jobId " + jobExecution.getJobId());
        }else{
            log.error("jobName {} not found", jobName);
            throw new InvalidNameException("Invalid job name");
        }
    }
}

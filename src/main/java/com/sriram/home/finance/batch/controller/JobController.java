package com.sriram.home.finance.batch.controller;

import com.sriram.home.finance.batch.model.JobParamRequest;
import com.sriram.home.finance.batch.service.JobService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/job")
public class JobController {

    private final JobService jobService;
    private final JobOperator jobOperator;

    @Autowired
    public JobController(JobService jobService, JobOperator jobOperator) {
        this.jobService = jobService;
        this.jobOperator = jobOperator;
    }

    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, InvalidNameException {
        jobService.triggerJob(jobName, null);
        return "Job " + jobName + " Started";
    }

    @PostMapping("/start/{jobName}/params")
    public String startJobWithParam(@PathVariable String jobName, @RequestBody List<JobParamRequest> jobParamRequestList) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, InvalidNameException {
        jobService.triggerJob(jobName, jobParamRequestList);
        return "Job " + jobName + " Started";
    }


    @GetMapping("/start/job/{executionId}")
    public String stopJob(@PathVariable Long executionId) throws NoSuchJobExecutionException, JobExecutionNotRunningException {
        jobOperator.stop(executionId);
        return "Execution id " + executionId + " Stopped";
    }
}

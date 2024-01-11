package com.sriram.home.finance.batch.scheduler;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CsvToDbJobScheduler {

    private final JobLauncher jobLauncher;
    private final Job csvToDbStudentJob;

    @Autowired
    public CsvToDbJobScheduler(JobLauncher jobLauncher, Job csvToDbStudentJob) {
        this.jobLauncher = jobLauncher;
        this.csvToDbStudentJob = csvToDbStudentJob;
    }

    //@Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void csvToDbStudentJobScheduler() {

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        JobParameters jobParameters = jobParametersBuilder
                .addLocalDateTime("TimesNow", LocalDateTime.now())
                .addJobParameter("myTimeParam", new JobParameter(LocalDateTime.now(), LocalDateTime.class))
                .toJobParameters();

        JobExecution jobExecution = null;
        try {
            jobExecution = jobLauncher.run(csvToDbStudentJob, jobParameters);
        } catch (Exception e) {
            System.out.println("Exception .....");
        }

        System.out.println("Job execution id " + jobExecution.getId() + " jobId " + jobExecution.getJobId());
    }
}

package com.sriram.home.finance.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
//This annotation is needed only for Spring batch - 4
//@EnableBatchProcessing

public class ShfBatchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShfBatchServiceApplication.class, args);
	}

}

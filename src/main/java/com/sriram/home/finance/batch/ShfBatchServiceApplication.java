package com.sriram.home.finance.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//This annotation is needed only for Spring batch - 4
//@EnableBatchProcessing
//@ComponentScan("com.sriram.home.finance.batch.config")
public class ShfBatchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShfBatchServiceApplication.class, args);
	}

}

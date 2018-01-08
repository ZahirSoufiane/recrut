package com.tnourji.recrut;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class BdTestUmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BdTestUmlApplication.class, args);
	}
	 @Bean
	    public Executor asyncExecutor() {
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(2);
	        executor.setMaxPoolSize(8);
	        executor.setQueueCapacity(500);
	        executor.setThreadNamePrefix("innovarecruit-");
	        executor.initialize();
	        return executor;
	    }
}

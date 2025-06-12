package com.equals.concilia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
		exclude = { DataSourceAutoConfiguration.class }
)
public class ConciliaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConciliaApplication.class, args);
	}

}

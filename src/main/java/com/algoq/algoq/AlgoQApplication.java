package com.algoq.algoq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@ComponentScan("com.algoq.algoq")
public class AlgoQApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoQApplication.class, args);
	}
}

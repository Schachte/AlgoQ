package com.algoq.algoq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlgoQApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoQApplication.class, args);
	}
}

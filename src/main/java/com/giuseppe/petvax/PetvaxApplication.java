package com.giuseppe.petvax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PetvaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetvaxApplication.class, args);
	}

}

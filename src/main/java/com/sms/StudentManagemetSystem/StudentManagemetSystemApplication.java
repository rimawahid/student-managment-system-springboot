package com.sms.StudentManagemetSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.sms")
@ComponentScan("com.sms")
@EnableJpaRepositories("com.sms")
public class StudentManagemetSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagemetSystemApplication.class, args);
		System.out.println("success");
	}

}

package com.nhnacademy.edu.springboot.student;

import com.nhnacademy.edu.springboot.student.config.SystemAuthorProperties;
import com.nhnacademy.edu.springboot.student.config.SystemVersionProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SystemAuthorProperties.class, SystemVersionProperties.class})
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}
}

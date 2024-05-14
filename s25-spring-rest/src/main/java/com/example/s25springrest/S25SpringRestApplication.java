package com.example.s25springrest;

import com.example.s25springrest.entity.Employee;
import com.example.s25springrest.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class S25SpringRestApplication {
	private static final Logger log = LoggerFactory.getLogger(S25SpringRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(S25SpringRestApplication.class, args);

		System.out.println("http://localhost:8080/employees");
	}

	@Bean
	public CommandLineRunner initDatabase(EmployeeRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
			log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
			log.info("Preloading " + repository.save(new Employee("tester", "tester")));
		};
	}
}

package com.s26springrest;

import com.s26springrest.entity.Employee;
import com.s26springrest.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class S26SpringRestApplication {
	private static final Logger log = LoggerFactory.getLogger(S26SpringRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(S26SpringRestApplication.class, args);

		System.out.println("http://localhost:8080/test");
		System.out.println();
		System.out.println();
		System.out.println("https://spring.io/guides/tutorials/rest");
		System.out.println();
		System.out.println("http://localhost:8080/employees");
		System.out.println("http://localhost:8080/employees/v2");
		System.out.println("http://localhost:8080/employees/v3");
	}

//	@Bean
//	public CommandLineRunner initDatabase(EmployeeRepository repository) {
//		return args -> {
//			log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
//			log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
//			log.info("Preloading " + repository.save(new Employee("tester", "tester")));
//			log.info("Preloading " + repository.save(new Employee("Bilbo", "Baggins", "burglar")));
//			log.info("Preloading " + repository.save(new Employee("Frodo", "Baggins", "thief")));
//			log.info("Preloading " + repository.save(new Employee("tester", "tessstr", "tester")));
//		};
//	}
}

package com.s27springrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S27SpringRestApplication {
	private static final Logger log = LoggerFactory.getLogger(S27SpringRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(S27SpringRestApplication.class, args);

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

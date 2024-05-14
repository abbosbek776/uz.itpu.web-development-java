package org.example.e1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        SomeService someService = context.getBean(SomeService.class);

        UserDto userDto = new UserDto();
        userDto.setLogin("PII_login");
        userDto.setPassword("PII_password");
        UserDto returnedUser = someService.login(userDto);
        System.out.println(returnedUser);

        System.out.println("=".repeat(40));

        someService.textLength("12345");
    }
}

package org.example.e4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        LogicV4 logic = context.getBean(LogicV4.class);

        logic.perform();
        logic.textLength("asdasd");
        logic.textLength("asdasd", 123);
    }
}

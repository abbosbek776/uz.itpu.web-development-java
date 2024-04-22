package org.example.e3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        LogicV3 logic = context.getBean(LogicV3.class);

        logic.perform();
        logic.textLength("asdasd");
    }
}

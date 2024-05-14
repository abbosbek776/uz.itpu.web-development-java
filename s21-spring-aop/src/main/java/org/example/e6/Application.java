package org.example.e6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        LogicV6 logic = context.getBean(LogicV6.class);

        logic.combineValues("asd", 123);
        logic.combineValuesV2("asd", 123);
    }
}

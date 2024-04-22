package org.example.e5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        LogicV5 logic = context.getBean(LogicV5.class);

        logic.perform();
        logic.textLength("asdasd");
        logic.returnText("some text");
        logic.returnConstantText();
    }
}

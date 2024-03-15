package org.itpu.home.task;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);) {
            Task task = context.getBean(Task.class);

            System.out.println(task);
        }
    }
}

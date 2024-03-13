package org.itpu.home.task;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

public class AppConfig {
    @Bean
    public Task task(@Qualifier("assignee") Employee e1,
                     @Qualifier("reviewer") Employee e2) {
        Task task = new Task();
        task.setAssignee(e1);
        task.setReviewer(e2);
        task.setDescription("New feature");
        return task;
    }

    @Bean(name = "assignee")
    public Employee employee1() {
        Employee assignee = new Employee();
        assignee.setName("John Doe");
        assignee.setPosition("Junior Software Engineer");
        return assignee;
    }

    @Bean(name = "reviewer")
    public Employee employee2() {
        Employee reviewer = new Employee();
        reviewer.setName("Emily Brown");
        reviewer.setPosition("Senior Software Engineer");
        return reviewer;
    }

}

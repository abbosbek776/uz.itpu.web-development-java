package org.itpu.home.task;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

public class AppConfig {
    @Bean
    public Task task(@Qualifier("assignee") Employee arg1,
                     @Qualifier("reviewer") Employee arg2) {
        Task task = new Task();
        task.setAssignee(arg1);
        task.setReviewer(arg2);
        task.setDescription("New feature");
        return task;
    }

    @Bean
    public Employee assignee() {
        Employee assignee = new Employee();
        assignee.setName("John Doe");
        assignee.setPosition("Junior Software Engineer");
        return assignee;
    }

    @Bean
    public Employee reviewer() {
        Employee reviewer = new Employee();
        reviewer.setName("Emily Brown");
        reviewer.setPosition("Senior Software Engineer");
        return reviewer;
    }

}

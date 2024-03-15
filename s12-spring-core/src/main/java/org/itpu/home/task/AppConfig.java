package org.itpu.home.task;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

public class AppConfig {
    @Bean
    public Task task(@Qualifier("employee1") Employee assignee,
                     @Qualifier("employee2") Employee reviewer) {
        Task task = new Task();
        task.setAssignee(assignee);
        task.setReviewer(reviewer);
        task.setDescription("New feature");
        return task;
    }

    @Bean
    public Employee employee1() {
        return getEmployee("John Doe", "Junior Software Engineer");
    }

    @Bean
    public Employee employee2() {
        return getEmployee("Emily Brown", "Senior Software Engineer");
    }

    private Employee getEmployee(String name, String position) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setPosition(position);
        return employee;
    }

}

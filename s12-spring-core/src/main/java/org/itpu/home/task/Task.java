package org.itpu.home.task;

public class Task {
    private String description;
    private Employee assignee;
    private Employee reviewer;

    // Getter and Setter methods for description, assignee and reviewer

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getAssignee() {
        return assignee;
    }

    public void setAssignee(Employee assignee) {
        this.assignee = assignee;
    }

    public Employee getReviewer() {
        return reviewer;
    }

    public void setReviewer(Employee reviewer) {
        this.reviewer = reviewer;
    }
}


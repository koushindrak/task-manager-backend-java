package com.todo.ses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class EmailTask {
    private String email;
    private Long taskId;
    private String taskName;
    private LocalDate taskDueDate;
    private String groupName;
    private String projectName;

    public EmailTask(String taskName, LocalDate taskDueDate, String groupName, String projectName) {
        this.taskName = taskName;
        this.taskDueDate = taskDueDate;
        this.groupName = groupName;
        this.projectName = projectName;
    }
}
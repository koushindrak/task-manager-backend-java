package com.todo.ses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class EmailTasks {
    private String taskName;
    private String taskDueDate;
    private String taskDescription;
    private String groupName;
    private String projectName;
}

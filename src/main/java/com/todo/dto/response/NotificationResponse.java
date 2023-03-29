package com.todo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class NotificationResponse {
    private Long id;
    private String description;
    private Date sentAt;
    private String taskName;
    private Date taskDueDate;
    private String projectName;
    private String groupName;

}

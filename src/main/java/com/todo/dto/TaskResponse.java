package com.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todo.entity.Task;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Data
public class TaskResponse {

    private Long id;

    private String name;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private Long userId;

    private String userName;

    private Long labelId;

    private String labelName;

    private Long priorityId;

    private String priorityName;

    public TaskResponse(Task task) {
        BeanUtils.copyProperties(task, this);
    }

    // constructors, getters and setters
}
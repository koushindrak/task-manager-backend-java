package com.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todo.entity.Label;
import com.todo.entity.Task;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TaskResponse {

    private Long id;

    private String name;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private List<TaskLabelResponse> labels;

    private String priority;
}
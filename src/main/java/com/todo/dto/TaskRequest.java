package com.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class TaskRequest {

    @NotBlank
    private String name;

    private String description;

    @FutureOrPresent(message = "Task can be created for Present or Future dates")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date dueDate;

    private Long groupId;

    private List<Long> labelIds;

    private String priority;

    private String status;
}
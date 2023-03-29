package com.todo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todo.constants.Priority;
import com.todo.constants.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @NotBlank(message = "Task name can not be blank")
    private String name;

    private String description;

    @FutureOrPresent(message = "Task can be created for Present or Future dates")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date dueDate;

    private Long groupId;

    private List<Long> labelIds;


    @NotNull(message = "Priority can not be null")
    private Priority priority;

    @NotBlank(message = "Task status can be - TODO, INPROGRESS OR DONE")
    @Schema(allowableValues = "TODO, INPROGRESS, DONE")
    private TaskStatus status;
}
package com.todo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todo.annotation.EnumNamePattern;
import com.todo.constants.Priority;
import com.todo.constants.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class TaskRequest {

    @NotBlank(message = "Task name can not be blank")
    private String name;
    @Size(min = 0, max = 30)
    private String description;

    @FutureOrPresent(message = "Task can be created for Present or Future dates")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date dueDate;

    @Schema(example = "1")
    private Long groupId;

    private Long projectId;

    private List<Long> labelIds;


    @NotNull(message = "Priority can not be null")
    private Priority priority;

    @NotNull(message = "Task status can be - TODO, INPROGRESS OR DONE")
    @Schema(allowableValues = "TODO, INPROGRESS, DONE")
    @EnumNamePattern(regexp = "TODO|INPROGRESS|DONE")
    private TaskStatus status;
}
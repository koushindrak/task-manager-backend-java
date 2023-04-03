package com.todo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todo.constants.ProjectStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectRequest {
    @NotBlank(message = "Project name is mandatory")
    private String name;

    @NotBlank
    private String description;

    @FutureOrPresent(message = "Project start date can be Present or Future dates only")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date startDate;

    @Future(message = "Project start date can be Future dates only")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date endDate;

    @NotNull(message = "Project status can be ACTIVE or INACTIVE")
    private ProjectStatus status = ProjectStatus.ACTIVE;
}

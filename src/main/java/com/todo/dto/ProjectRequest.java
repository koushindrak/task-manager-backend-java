package com.todo.dto;

import com.todo.constants.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    private ProjectStatus status = ProjectStatus.ACTIVE;
}

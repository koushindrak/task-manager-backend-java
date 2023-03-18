package com.todo.dto;

import com.todo.entity.Project;
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

    @NotBlank
    private String status;

    @NotNull
    private Long userId;

    // getters and setters
    public Project toProject(ProjectRequest projectRequest) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setStatus(status);
        // project.setUser(user);
        return project;
    }
}

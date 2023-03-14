package com.todo.dto;

import com.todo.entity.Project;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String status;
    private Long userId;

    public ProjectResponse(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.status = project.getStatus();
        this.userId = project.getUser().getId();
    }
}

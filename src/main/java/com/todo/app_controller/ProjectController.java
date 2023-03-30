package com.todo.app_controller;

import com.todo.business.ProjectService;
import com.todo.dto.request.ProjectRequest;
import com.todo.dto.response.ProjectResponse;
import com.todo.dto.response.SuccessResponse;
import com.todo.entity.Project;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessResponse<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest projectRequest) {
        ProjectResponse project = projectService.createProject(projectRequest);
        return new SuccessResponse().created(project, Project.class);
    }


    @GetMapping("/{id}")
    public SuccessResponse<ProjectResponse> getProjectById(@PathVariable Long id) {
        ProjectResponse project = projectService.getProjectById(id);
        return new SuccessResponse<ProjectResponse>().retrieved(project, Project.class);
    }

    @GetMapping
    public SuccessResponse<List<ProjectResponse>> getAllProjects() {
        List<ProjectResponse> projects = projectService.getAllProjects();
        return new SuccessResponse<List<ProjectResponse>>().retrieved(projects, Project.class);
    }


    @PutMapping("/{id}")
    public SuccessResponse<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectRequest projectRequest) {
        ProjectResponse project = projectService.updateProject(id, projectRequest);
        return new SuccessResponse<ProjectResponse>().updated(project, Project.class);
    }

    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SuccessResponse<ProjectResponse> deleteProject(@PathVariable Long id) {
        return new SuccessResponse<ProjectResponse>().deleted(projectService.deleteProject(id), ProjectResponse.class);
    }
}

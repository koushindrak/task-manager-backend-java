package com.todo.app_controller;

import com.todo.business.ProjectService;
import com.todo.dto.ProjectRequest;
import com.todo.dto.ProjectResponse;
import com.todo.dto.ResponseDTO;
import com.todo.dto.ResponseDTO.SuccessResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessResponse createProject(@RequestBody @Valid ProjectRequest projectRequest) {
        ProjectResponse project = projectService.createProject(projectRequest);
        return new ResponseDTO().created(project, ProjectResponse.class);
    }


    @GetMapping("/{id}")
    public SuccessResponse getProjectById(@PathVariable Long id) {
        ProjectResponse project = projectService.getProjectById(id);
        return new ResponseDTO().retrieved(project, ProjectResponse.class);
    }

    @GetMapping
    public SuccessResponse getAllProjects() {
        List<ProjectResponse> projects = projectService.getAllProjects();
        return new ResponseDTO().retrieved(projects, ProjectResponse.class);
    }



    @PutMapping("/{id}")
    public SuccessResponse updateProject(@PathVariable Long id, @RequestBody @Valid ProjectRequest projectRequest) {
        ProjectResponse project = projectService.updateProject(id, projectRequest);
        return new ResponseDTO().updated(project, ProjectResponse.class);
    }

    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SuccessResponse deleteProject(@PathVariable Long id) {
        return new ResponseDTO().deleted(projectService.deleteProject(id), ProjectResponse.class);
    }
}

package com.todo.business;

import com.todo.a_utils.ProjectUtils;
import com.todo.dao.ProjectRepository;
import com.todo.dto.ProjectRequest;
import com.todo.dto.ProjectResponse;
import com.todo.entity.Project;
import com.todo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectUtils projectUtils;

    public ProjectResponse createProject(ProjectRequest projectRequest) {

        Project project = projectUtils.toProject(projectRequest);
        project = projectRepository.save(project);
        return projectUtils.toProjectResponse(project);
    }

    public List<ProjectResponse> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(project ->  projectUtils.toProjectResponse(project))
                .collect(Collectors.toList());
    }

    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        return projectUtils.toProjectResponse(project);
    }

    public ProjectResponse updateProject(Long id, ProjectRequest projectRequest) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid project id"));
        BeanUtils.copyProperties(projectRequest,project);
        projectRepository.save(project);
        return projectUtils.toProjectResponse(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}

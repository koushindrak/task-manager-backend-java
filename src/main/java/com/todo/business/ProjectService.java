package com.todo.business;

import com.todo.dao.ProjectRepository;
import com.todo.dto.ProjectRequest;
import com.todo.dto.ProjectResponse;
import com.todo.entity.Project;
import com.todo.entity.User;
import com.todo.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectResponse> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(project -> new ProjectResponse(project))
                .collect(Collectors.toList());
    }

    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        return new ProjectResponse(project);
    }

    public ProjectResponse createProject(ProjectRequest projectRequest, User user) {
        Project project = projectRequest.toProject(projectRequest);
        project.setUser(user);
        project = projectRepository.save(project);
        return new ProjectResponse(project);
    }

    public ProjectResponse updateProject(Long id, ProjectRequest projectRequest) {
        Project project = projectRequest.toProject(projectRequest);
        project.setId(id);
        project = projectRepository.save(project);
        return new ProjectResponse(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}

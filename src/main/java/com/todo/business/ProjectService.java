package com.todo.business;

import com.todo.a_utils.CommonUtils;
import com.todo.a_utils.ProjectUtils;
import com.todo.dao.ProjectRepository;
import com.todo.dto.request.ProjectRequest;
import com.todo.dto.response.ProjectResponse;
import com.todo.dto.response.TaskResponse;
import com.todo.entity.Project;
import com.todo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectUtils projectUtils;
    private final CommonUtils commonUtils;
    private final TaskService taskService;

    public ProjectResponse createProject(ProjectRequest projectRequest) {

        Project project = projectUtils.toProject(projectRequest);
        project.setUser(commonUtils.getCurrentUser());
        project = projectRepository.save(project);
        return projectUtils.toProjectResponse(project);
    }

    public List<ProjectResponse> getAllProjects() {
        List<Project> projects = projectRepository.getProjectsByUser_Id(CommonUtils.getLoggedInUserId());
        return projects.stream()
                .map(project -> projectUtils.toProjectResponse(project))
                .collect(Collectors.toList());
    }

    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findProjectByIdAndUser_Id(id, CommonUtils.getLoggedInUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        return projectUtils.toProjectResponse(project);
    }

    public ProjectResponse updateProject(Long id, ProjectRequest projectRequest) {
        Project project = projectRepository.findProjectByIdAndUser_Id(id, CommonUtils.getLoggedInUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        BeanUtils.copyProperties(projectRequest, project);
        projectRepository.save(project);
        return projectUtils.toProjectResponse(project);
    }

    public ProjectResponse deleteProject(Long id) {
        Project project = projectRepository.findProjectByIdAndUser_Id(id, CommonUtils.getLoggedInUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        projectRepository.delete(project);
        return projectUtils.toProjectResponse(project);
    }

    public List<TaskResponse> getTaskListByProjectId(Long projectId) {
        List<TaskResponse> taskResponses = taskService.getTasksByProjectId(projectId);
        return taskResponses;
    }
}

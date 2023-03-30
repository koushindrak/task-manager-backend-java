package com.todo.a_utils;

import com.todo.context.ExecutionContext;
import com.todo.dao.UserRepository;
import com.todo.dto.request.ProjectRequest;
import com.todo.dto.response.ProjectResponse;
import com.todo.entity.Project;
import com.todo.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProjectUtils {

    private final UserRepository userRepository;

    public Project toProject(ProjectRequest projectRequest) {
        Project project = new Project();
        BeanUtils.copyProperties(projectRequest, project);
        User user = userRepository.findById(ExecutionContext.get().getUsercontext().id()).get();
        project.setUser(user);
        return project;
    }

    public ProjectResponse toProjectResponse(Project project) {
        ProjectResponse projectResponse = new ProjectResponse();
        BeanUtils.copyProperties(project, projectResponse);
        projectResponse.setStatus(project.getStatus().name());
        return projectResponse;
    }
}

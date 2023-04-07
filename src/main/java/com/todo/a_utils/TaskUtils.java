package com.todo.a_utils;

import com.todo.constants.TaskStatus;
import com.todo.dao.*;
import com.todo.dto.request.TaskRequest;
import com.todo.dto.response.TaskLabelResponse;
import com.todo.dto.response.TaskResponse;
import com.todo.entity.Group;
import com.todo.entity.Label;
import com.todo.entity.Project;
import com.todo.entity.Task;
import com.todo.exceptions.ResourceNotFoundException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Component
@Data
@Slf4j
public class TaskUtils {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;
    private final GroupRepository groupRepository;
    private final ProjectRepository projectRepository;


    public static TaskResponse toTaskResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        BeanUtils.copyProperties(task, taskResponse,"labels");
        List<TaskLabelResponse> labels = new ArrayList<>();
        taskResponse.setStatus(task.getTaskStatus());
        log.info("Labels for task id " + task.getId() + " are-" + task.getLabels());
        setLabels(task, taskResponse, labels);
        taskResponse.setDueDate(task.getDueDate());
        taskResponse.setPriority(task.getPriority().name());
        setGroup(task, taskResponse);
        setProject(task, taskResponse);
        return taskResponse;
    }

    private static void setProject(Task task, TaskResponse taskResponse) {
        if (task.getProject() != null) {
            taskResponse.setProjectId(task.getProject().getId());
            taskResponse.setProjectName(task.getProject().getName());
        }
    }

    private static void setGroup(Task task, TaskResponse taskResponse) {
        if (task.getGroup() != null) {
            taskResponse.setGroupId(task.getGroup().getId());
            taskResponse.setGroupName(task.getGroup().getName());
        }
    }

    private static void setLabels(Task task, TaskResponse taskResponse, List<TaskLabelResponse> labels) {
        if (Objects.nonNull(task.getLabels())) {
            task.getLabels().forEach(label -> {
                TaskLabelResponse taskLabelResponse = new TaskLabelResponse();
                taskLabelResponse.setLabelId(label.getId());
                taskLabelResponse.setLabelName(label.getName());
                labels.add(taskLabelResponse);
            });
            taskResponse.setLabels(labels);
        }
    }

    public TaskResponse saveOrUpdateTask(TaskRequest taskRequest, Task task) {
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
        if (taskRequest.getGroupId() != null) {
            Group group = groupRepository.findById(taskRequest.getGroupId())
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid group id"));
            task.setGroup(group);
        }

        if (Objects.nonNull(taskRequest.getStatus())) {
            task.setTaskStatus(taskRequest.getStatus());
        } else {
            task.setTaskStatus(TaskStatus.TODO);
        }

        if (!CollectionUtils.isEmpty(taskRequest.getLabelIds())) {
            List<Label> labels = labelRepository.findAllById(taskRequest.getLabelIds());
            task.setLabels(new HashSet<>(labels));
        }

        if (taskRequest.getPriority() != null) {
            task.setPriority(taskRequest.getPriority());
        }

        if (taskRequest.getProjectId() != null) {
            Project project = projectRepository.findProjectByIdAndUser_Id(taskRequest.getProjectId(), CommonUtils.getLoggedInUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid Project Id"));
            task.setProject(project);
        }

        Task task1 = taskRepository.save(task);
        return TaskUtils.toTaskResponse(task1);
    }


}

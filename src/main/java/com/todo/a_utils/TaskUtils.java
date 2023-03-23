package com.todo.a_utils;

import com.todo.constants.Priority;
import com.todo.constants.TaskStatus;
import com.todo.dao.LabelRepository;
import com.todo.dao.TaskRepository;
import com.todo.dao.UserRepository;
import com.todo.dto.TaskLabelResponse;
import com.todo.dto.TaskRequest;
import com.todo.dto.TaskResponse;
import com.todo.entity.Label;
import com.todo.entity.Task;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.ZoneId;
import java.util.*;

@Component
@Data
@Slf4j
public class TaskUtils {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;

    public static TaskResponse toTaskResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        BeanUtils.copyProperties(task, taskResponse);
        List<TaskLabelResponse> labels = new ArrayList<>();
        log.info("Labels for task id "+task.getId()+" are-"+task.getLabels());
        task.getLabels().forEach(label -> {
            TaskLabelResponse taskLabelResponse = new TaskLabelResponse();
            taskLabelResponse.setLabelId(label.getId());
            taskLabelResponse.setLabelName(label.getName());
            labels.add(taskLabelResponse);
        });
        taskResponse.setLabels(labels);
        taskResponse.setPriority(task.getPriority().name());
        return taskResponse;
    }
    public Task saveOrUpdateTask(TaskRequest taskRequest, Task task) {
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(Date.from(taskRequest.getDueDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        if(Objects.nonNull(taskRequest.getStatus())){
            task.setTaskStatus(TaskStatus.valueOf(taskRequest.getStatus()));
        }else {
            task.setTaskStatus(TaskStatus.TODO);
        }

        if (!CollectionUtils.isEmpty(taskRequest.getLabelIds())) {
            List<Label> labels = labelRepository.findAllById(taskRequest.getLabelIds());
            task.setLabels(new HashSet<>(labels));
        }

        if (taskRequest.getPriority() != null) {
            task.setPriority(Priority.valueOf(taskRequest.getPriority()));
        }

        return taskRepository.save(task);
    }


}

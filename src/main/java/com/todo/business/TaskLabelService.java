package com.todo.business;

import com.todo.a_utils.LabelUtils;
import com.todo.a_utils.TaskUtils;
import com.todo.dao.LabelRepository;
import com.todo.dao.TaskRepository;
import com.todo.dto.request.TaskLabelRequest;
import com.todo.dto.response.LabelResponse;
import com.todo.dto.response.TaskResponse;
import com.todo.entity.Label;
import com.todo.entity.Task;
import com.todo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskLabelService {

    private final TaskRepository taskRepository;
    private final LabelRepository labelRepository;

    public void addLabelOnTask(TaskLabelRequest taskLabelRequest) {
        labelRepository.findById(taskLabelRequest.labelId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Label Id"));
        taskRepository.findById(taskLabelRequest.taskId()).orElseThrow(()-> new ResourceNotFoundException("Invalid task id"));

        taskRepository.addLabelOnTask(taskLabelRequest.taskId(), taskLabelRequest.labelId());
    }

    public void removeLabelFromTask(TaskLabelRequest taskLabelRequest) {
        labelRepository.findById(taskLabelRequest.labelId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Label Id"));
        taskRepository.findById(taskLabelRequest.taskId()).orElseThrow(()-> new ResourceNotFoundException("Invalid task id"));

        taskRepository.removeLabelFromTask(taskLabelRequest.taskId(), taskLabelRequest.labelId());
    }

    public List<TaskResponse> getTasksByLabel(Long labelId) {
        labelRepository.findById(labelId).orElseThrow(()-> new ResourceNotFoundException("Invalid Label Id"));
        List<Task> tasks = taskRepository.findTaskByLabels_Id(labelId);
        List<TaskResponse> taskResponses = tasks.stream().map(TaskUtils::toTaskResponse).collect(Collectors.toList());
        return taskResponses;
    }

    public List<LabelResponse> getLabelsByTask(Long taskId) {
        taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Invalid task id"));
        List<Label> labels = labelRepository.findAllByTasks_Id(taskId);
        List<LabelResponse> labelResponses = labels.stream().map(LabelUtils::toLabelResponse).collect(Collectors.toList());
        return labelResponses;
    }
}

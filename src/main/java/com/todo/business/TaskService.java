package com.todo.business;

import com.todo.dao.TaskRepository;
import com.todo.dto.TaskRequest;
import com.todo.dto.TaskResponse;
import com.todo.entity.Task;
import com.todo.a_utils.TaskUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Data
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskUtils taskUtils;

    public Task createTask(TaskRequest taskRequest) {
        Task task = new Task();
        return taskUtils.saveOrUpdateTask(taskRequest, task);
    }
    public List<TaskResponse> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(task -> TaskUtils.toTaskResponse(task)).collect(Collectors.toList());
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        return TaskUtils.toTaskResponse(task);
    }

    public Task updateTask(Long id, TaskRequest taskRequest) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        return taskUtils.saveOrUpdateTask(taskRequest, task);
    }

    public TaskResponse deleteTask(Long id) {
        TaskResponse task = getTaskById(id);
        taskRepository.deleteById(id);
        return task;
    }
}

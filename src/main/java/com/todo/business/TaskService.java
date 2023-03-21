package com.todo.business;

import com.todo.a_utils.CommonUtils;
import com.todo.context.ExecutionContext;
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
    private final CommonUtils commonUtils;

    public Task createTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setUser(commonUtils.getCurrentUser());
        return taskUtils.saveOrUpdateTask(taskRequest, task);
    }
    public List<TaskResponse> getTasks() {
        List<Task> tasks = taskRepository.getTaskByUser_Id(CommonUtils.getLoggedInUserId());
        return tasks.stream().map(task -> TaskUtils.toTaskResponse(task)).collect(Collectors.toList());
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.getTaskByIdAndUser_Id(id,CommonUtils.getLoggedInUserId())
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        return TaskUtils.toTaskResponse(task);
    }

    public Task updateTask(Long id, TaskRequest taskRequest) {
        Task task = taskRepository.getTaskByIdAndUser_Id(id,CommonUtils.getLoggedInUserId())
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        return taskUtils.saveOrUpdateTask(taskRequest, task);
    }

    public TaskResponse deleteTask(Long id) {
        Task task = taskRepository.getTaskByIdAndUser_Id(id,CommonUtils.getLoggedInUserId())
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        taskRepository.delete(task);
        return TaskUtils.toTaskResponse(task);
    }
}

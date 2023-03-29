package com.todo.business;

import com.todo.a_utils.CommonUtils;
import com.todo.dao.TaskRepository;
import com.todo.dto.request.TaskRequest;
import com.todo.dto.response.TaskResponse;
import com.todo.entity.Task;
import com.todo.a_utils.TaskUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Data
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskUtils taskUtils;
    private final CommonUtils commonUtils;
    private final CommentService commentService;

    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setUser(commonUtils.getCurrentUser());
        return taskUtils.saveOrUpdateTask(taskRequest, task);
    }
    public List<TaskResponse> getTasks() {
        List<Task> tasks = taskRepository.findAllByUser_Id(CommonUtils.getLoggedInUserId());
        return tasks.stream().map(task -> TaskUtils.toTaskResponse(task)).collect(Collectors.toList());
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.getTaskByIdAndUser_Id(id,CommonUtils.getLoggedInUserId())
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        TaskResponse taskResponse = TaskUtils.toTaskResponse(task);
        taskResponse.setComments(commentService.getCommentsByTaskId(id));
        return taskResponse;
    }

    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
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

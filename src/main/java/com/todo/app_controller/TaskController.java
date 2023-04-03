package com.todo.app_controller;

import com.todo.business.TaskService;
import com.todo.dto.request.TaskRequest;
import com.todo.dto.response.SuccessResponse;
import com.todo.dto.response.TaskResponse;
import com.todo.entity.Task;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "5-Task-Controller", description = "Task can be linked with Groups, Projects, Labels")
@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @PostMapping
    public SuccessResponse<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return new SuccessResponse<TaskResponse>().created(taskService.createTask(taskRequest), Task.class);
    }

    @GetMapping
    public SuccessResponse<List<TaskResponse>> getTasks() {
        return new SuccessResponse().retrieved(taskService.getTasks(), Task.class);
    }

    @GetMapping("/{id}")
    public SuccessResponse<TaskResponse> getTaskById(@PathVariable("id") @Parameter(description = "Task ID", example = "1") Long id) {
        return new SuccessResponse<TaskResponse>().retrieved(taskService.getTaskById(id), Task.class);
    }

    @PutMapping("/{id}")
    public SuccessResponse<TaskResponse> updateTask(@PathVariable("id") Long id, @Valid @RequestBody TaskRequest taskRequest) {
        return new SuccessResponse().updated(taskService.updateTask(id, taskRequest), Task.class);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<TaskResponse> deleteTask(@PathVariable("id") @Parameter(description = "Task ID", example = "1") Long id) {
        return new SuccessResponse().deleted(taskService.deleteTask(id), Task.class);
    }

}

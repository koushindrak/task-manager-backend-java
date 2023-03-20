package com.todo.app_controller;

import com.todo.business.TaskService;
import com.todo.dto.ResponseDTO;
import com.todo.dto.ResponseDTO.SuccessResponse;
import com.todo.dto.TaskRequest;
import com.todo.entity.Task;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final ResponseDTO responseDTO;

    @PostMapping
    public SuccessResponse createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return responseDTO.created(taskService.createTask(taskRequest), Task.class);
    }

    @GetMapping
    public SuccessResponse getTasks() {
        return responseDTO.retrieved(taskService.getTasks(), Task.class);
    }

    @GetMapping("/{id}")
    public SuccessResponse getTaskById(@PathVariable("id") @Parameter(description = "Task ID", example = "1") Long id) {
        return responseDTO.retrieved(taskService.getTaskById(id), Task.class);
    }

    @PutMapping("/{id}")
    public SuccessResponse updateTask(@PathVariable("id") Long id, @Valid @RequestBody TaskRequest taskRequest) {
        return responseDTO.updated(taskService.updateTask(id, taskRequest), Task.class);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse deleteTask(@PathVariable("id") @Parameter(description = "Task ID", example = "1") Long id) {
        return responseDTO.deleted(taskService.deleteTask(id), Task.class);
    }

}

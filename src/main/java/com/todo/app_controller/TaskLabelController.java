package com.todo.app_controller;

import com.todo.business.TaskLabelService;
import com.todo.constants.CommonConstants;
import com.todo.dto.request.TaskLabelRequest;
import com.todo.dto.response.LabelResponse;
import com.todo.dto.response.SuccessResponse;
import com.todo.dto.response.TaskResponse;
import com.todo.entity.Task;
import com.todo.exceptions.ErrorMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "6- Task Label Controller", description = "Used for assigning,removing and views labels for task")
@RestController
@RequestMapping("/api/v1/tasks-labels")
@AllArgsConstructor
public class TaskLabelController {

    private final TaskLabelService taskLabelService;

    @PutMapping("/add-label")
    public SuccessResponse addLabelOnTask(@Valid @RequestBody TaskLabelRequest taskLabelRequest) {
        taskLabelService.addLabelOnTask(taskLabelRequest);
        return new SuccessResponse().ok();
    }

    @PutMapping("/remove-label")
    public SuccessResponse removeLabelFromTask(@Valid @RequestBody TaskLabelRequest taskLabelRequest) {
        taskLabelService.removeLabelFromTask(taskLabelRequest);
        return new SuccessResponse().ok();
    }

    @GetMapping("/task-by-label")
    public SuccessResponse getTaskByLabel(@RequestParam Long labelId) {
        List<TaskResponse> taskResponses = taskLabelService.getTasksByLabel(labelId);
        return new SuccessResponse().ok(taskResponses, CommonConstants.SUCCESS_RESPONSE);
    }

    @GetMapping("/label-by-task")
    public SuccessResponse getLabelsByTask(@RequestParam Long taskId) {
        List<LabelResponse> labelResponses = taskLabelService.getLabelsByTask(taskId);
        return new SuccessResponse().ok(labelResponses, CommonConstants.SUCCESS_RESPONSE);
    }
}

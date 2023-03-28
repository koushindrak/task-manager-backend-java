package com.todo.app_controller;

import com.todo.business.TaskLabelService;
import com.todo.dto.*;
import com.todo.entity.Task;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks-labels")
@AllArgsConstructor
public class TaskLabelController {

    private final TaskLabelService taskLabelService;

    @PutMapping("/add-label")
    public SuccessResponse addLabelOnTask(@Valid @RequestBody TaskLabelRequest taskLabelRequest){
        taskLabelService.addLabelOnTask(taskLabelRequest);
        return new SuccessResponse().updated("Label added to task ", Task.class);
    }

    @PutMapping("/remove-label")
    public SuccessResponse removeLabelFromTask(@Valid @RequestBody TaskLabelRequest taskLabelRequest){
        taskLabelService.removeLabelFromTask(taskLabelRequest);
        return new SuccessResponse().updated("Label Removed from task ", Task.class);
    }
    @GetMapping("/task-by-label")
    public SuccessResponse getTaskByLabel(@RequestParam Long labelId){
        List<TaskResponse> taskResponses = taskLabelService.getTasksByLabel(labelId);
        return new SuccessResponse().retrieved(taskResponses,Task.class);
    }

    @GetMapping("/label-by-task")
    public SuccessResponse getLabelsByTask(@RequestParam Long taskId){
        List<LabelResponse> labelResponses = taskLabelService.getLabelsByTask(taskId);
        return new SuccessResponse().retrieved(labelResponses,Task.class);
    }
}

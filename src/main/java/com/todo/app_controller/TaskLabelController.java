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

@RestController("task-label")
@AllArgsConstructor
public class TaskLabelController {

    private final TaskLabelService taskLabelService;

    @PutMapping("/add-label")
    public ResponseDTO.SuccessResponse addLabelOnTask(@Valid @RequestBody TaskLabelRequest taskLabelRequest){
        taskLabelService.addLabelOnTask(taskLabelRequest);
        return new ResponseDTO().updated("Label added to task ", Task.class);
    }

    @PutMapping("/remove-label")
    public ResponseDTO.SuccessResponse removeLabelFromTask(@Valid @RequestBody TaskLabelRequest taskLabelRequest){
        taskLabelService.removeLabelFromTask(taskLabelRequest);
        return new ResponseDTO().updated("Label Removed from task ", Task.class);
    }
    @GetMapping
    public ResponseDTO.SuccessResponse getTaskByLabel(@RequestParam Long labelId){
        List<TaskResponse> taskResponses = taskLabelService.getTasksByLabel(labelId);
        return new ResponseDTO().retrieved(taskResponses,Task.class);
    }

    @GetMapping
    public ResponseDTO.SuccessResponse getLabelsByTask(@RequestParam Long taskId){
        List<LabelResponse> labelResponses = taskLabelService.getLabelsByTask(taskId);
        return new ResponseDTO().retrieved(labelResponses,Task.class);
    }
}

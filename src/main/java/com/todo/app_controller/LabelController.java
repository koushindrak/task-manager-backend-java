package com.todo.app_controller;

import com.todo.business.LabelService;
import com.todo.dto.request.LabelRequest;
import com.todo.dto.response.LabelResponse;
import com.todo.dto.response.SuccessResponse;
import com.todo.entity.Label;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/labels")
@AllArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessResponse<LabelResponse> createLabel(@RequestBody @Valid LabelRequest labelRequest) {
        LabelResponse label = labelService.createLabel(labelRequest);
        SuccessResponse<LabelResponse> successResponse = new SuccessResponse<LabelResponse>().created(label, Label.class);
        return successResponse;
    }


    @GetMapping("/{id}")
    public SuccessResponse<LabelResponse> getLabelById(@PathVariable Long id) {
        LabelResponse label = labelService.getLabelById(id);
        return new SuccessResponse<LabelResponse>().retrieved(label, Label.class);
    }

    @GetMapping
    public SuccessResponse<List<LabelResponse>> getAllLabels() {
        List<LabelResponse> labels = labelService.getAllLabels();
        return new SuccessResponse().retrieved(labels, Label.class);
    }



    @PutMapping("/{id}")
    public SuccessResponse updateLabel(@PathVariable Long id, @RequestBody @Valid LabelRequest labelRequest) {
        LabelResponse label = labelService.updateLabel(id, labelRequest);
        return new SuccessResponse().updated(label, Label.class);
    }

    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SuccessResponse deleteLabel(@PathVariable Long id) {
       return new SuccessResponse().deleted(labelService.deleteLabel(id), Label.class);
    }
}

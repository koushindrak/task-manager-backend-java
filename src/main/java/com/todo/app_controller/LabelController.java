package com.todo.app_controller;

import com.todo.business.LabelService;
import com.todo.dto.LabelRequest;
import com.todo.dto.LabelResponse;
import com.todo.dto.ResponseDTO;
import com.todo.dto.ResponseDTO.SuccessResponse;
import com.todo.entity.Label;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/labels")
@AllArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessResponse createLabel(@RequestBody @Valid LabelRequest labelRequest) {
        LabelResponse label = labelService.createLabel(labelRequest);
        SuccessResponse successResponse = new ResponseDTO().created(label, LabelResponse.class);
        return successResponse;
    }


    @GetMapping("/{id}")
    public SuccessResponse getLabelById(@PathVariable Long id) {
        LabelResponse label = labelService.getLabelById(id);
        return new ResponseDTO().retrieved(label, LabelResponse.class);
    }

    @GetMapping
    public SuccessResponse getAllLabels() {
        List<LabelResponse> labels = labelService.getAllLabels();
        return new ResponseDTO().retrieved(labels, LabelResponse.class);
    }



    @PutMapping("/{id}")
    public SuccessResponse updateLabel(@PathVariable Long id, @RequestBody @Valid LabelRequest labelRequest) {
        LabelResponse label = labelService.updateLabel(id, labelRequest);
        return new ResponseDTO().updated(label, LabelResponse.class);
    }

    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SuccessResponse deleteLabel(@PathVariable Long id) {
       return new ResponseDTO().deleted(labelService.deleteLabel(id), Label.class);
    }
}

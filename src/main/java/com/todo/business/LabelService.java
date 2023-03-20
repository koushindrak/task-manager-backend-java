package com.todo.business;

import com.todo.a_utils.LabelUtils;
import com.todo.dao.LabelRepository;
import com.todo.dto.LabelRequest;
import com.todo.dto.LabelResponse;
import com.todo.entity.Label;
import com.todo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;
    private final LabelUtils labelUtils;

    public LabelResponse createLabel(LabelRequest labelRequest) {

        Label label = labelUtils.toLabel(labelRequest);
        label = labelRepository.save(label);
        return labelUtils.toLabelResponse(label);
    }

    public List<LabelResponse> getAllLabels() {
        List<Label> labels = labelRepository.findAll();
        return labels.stream()
                .map(label ->  labelUtils.toLabelResponse(label))
                .collect(Collectors.toList());
    }

    public LabelResponse getLabelById(Long id) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Label not found with id " + id));
        return labelUtils.toLabelResponse(label);
    }

    public LabelResponse updateLabel(Long id, LabelRequest labelRequest) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid label id"));
        BeanUtils.copyProperties(labelRequest,label);
        labelRepository.save(label);
        return labelUtils.toLabelResponse(label);
    }

    public void deleteLabel(Long id) {
        labelRepository.deleteById(id);
    }
}

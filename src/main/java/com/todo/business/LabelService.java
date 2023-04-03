package com.todo.business;

import com.todo.a_utils.CommonUtils;
import com.todo.a_utils.LabelUtils;
import com.todo.dao.LabelRepository;
import com.todo.dto.request.LabelRequest;
import com.todo.dto.response.LabelResponse;
import com.todo.entity.Label;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class LabelService {

    private final LabelRepository labelRepository;
    private final LabelUtils labelUtils;
    private final CommonUtils commonUtils;

    public LabelResponse createLabel(LabelRequest labelRequest) {
        Label label = labelUtils.toLabel(labelRequest);
        label.setUser(commonUtils.getCurrentUser());
        label = labelRepository.save(label);
        return LabelUtils.toLabelResponse(label);
    }

    public List<LabelResponse> getAllLabels() {
        List<Label> labels = labelRepository.findAllByUser_Id(CommonUtils.getLoggedInUserId());
        labels.forEach(label -> log.info("labelid-" + label.getId() + " Tasks are--" + label.getTasks()));

        return labels.stream()
                .map(label -> LabelUtils.toLabelResponse(label))
                .collect(Collectors.toList());
    }

    public LabelResponse getLabelById(Long id) {
        Label label = labelRepository.findLabelByIdAndUser_Id(id, CommonUtils.getLoggedInUserId())
                .orElseThrow(() -> new EntityNotFoundException("Label not found with id: " + id));
        return LabelUtils.toLabelResponse(label);
    }

    public LabelResponse updateLabel(Long id, LabelRequest labelRequest) {
        Label label = labelRepository.findLabelByIdAndUser_Id(id, CommonUtils.getLoggedInUserId())
                .orElseThrow(() -> new EntityNotFoundException("Label not found with id: " + id));
        BeanUtils.copyProperties(labelRequest, label);
        labelRepository.save(label);
        return LabelUtils.toLabelResponse(label);
    }

    public LabelResponse deleteLabel(Long id) {
        Label label = labelRepository.findLabelByIdAndUser_Id(id, CommonUtils.getLoggedInUserId())
                .orElseThrow(() -> new EntityNotFoundException("Label not found with id: " + id));
        labelRepository.delete(label);
        return LabelUtils.toLabelResponse(label);

    }
}

package com.todo.a_utils;

import com.todo.context.ExecutionContext;
import com.todo.dao.UserRepository;
import com.todo.dto.LabelRequest;
import com.todo.dto.LabelResponse;
import com.todo.entity.Label;
import com.todo.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LabelUtils {

    private final UserRepository userRepository;

    public Label toLabel(LabelRequest labelRequest) {
        Label label = new Label();
        BeanUtils.copyProperties(labelRequest,label);
        User currentUser = userRepository.findById(ExecutionContext.get().getUsercontext().id()).get();
        label.setUser(currentUser);
        return label;
    }

    public static LabelResponse toLabelResponse(Label label) {
        LabelResponse labelResponse = new LabelResponse();
        BeanUtils.copyProperties(label,labelResponse);
        return labelResponse;
    }
}

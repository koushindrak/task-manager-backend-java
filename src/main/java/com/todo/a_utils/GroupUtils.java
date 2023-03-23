package com.todo.a_utils;

import com.todo.context.ExecutionContext;
import com.todo.dao.UserRepository;
import com.todo.dto.GroupRequest;
import com.todo.entity.Group;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GroupUtils {

    private final UserRepository userRepository;

    public static Group toGroup(GroupRequest request) {
        Group group = new Group();
        BeanUtils.copyProperties(request,group);
//        group.setOwner(ExecutionContext.get().getUsercontext().id());
        return group;
    }

}

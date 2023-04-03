package com.todo.a_utils;

import com.todo.dto.request.GroupRequest;
import com.todo.dto.response.GroupResponse;
import com.todo.entity.Group;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GroupUtils {

    public static Group toGroup(GroupRequest request) {
        Group group = new Group();
        BeanUtils.copyProperties(request, group);
        group.setOwnerId(CommonUtils.getLoggedInUserId());
        group.setStatus(request.status());
        return group;
    }

    public static Group toGroup(Group group, GroupRequest request) {
        BeanUtils.copyProperties(request, group);
        group.setOwnerId(CommonUtils.getLoggedInUserId());
        group.setStatus(request.status());
        return group;
    }

    public static GroupResponse toGroupResponse(Group group) {
        GroupResponse groupResponse = new GroupResponse();
        BeanUtils.copyProperties(group, groupResponse);
        groupResponse.setStatus(group.getStatus().name());
        groupResponse.setUserIds(group.getUsers().stream().map(mem -> mem.getId()).collect(Collectors.toSet()));
        return groupResponse;
    }

}

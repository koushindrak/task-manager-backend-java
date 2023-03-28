package com.todo.business;

import com.todo.a_utils.CommonUtils;
import com.todo.a_utils.GroupUtils;
import com.todo.dao.GroupRepository;
import com.todo.dto.GroupRequest;
import com.todo.dto.GroupResponse;
import com.todo.entity.Group;
import com.todo.entity.User;
import com.todo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.todo.a_utils.CommonUtils.getLoggedInUserId;
import static com.todo.a_utils.GroupUtils.toGroup;
import static com.todo.a_utils.GroupUtils.toGroupResponse;

@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupResponse createGroup(GroupRequest groupRequest) {
        Group group = toGroup(groupRequest);
        group = groupRepository.save(group);
        return toGroupResponse(group);
    }
    public List<GroupResponse> getAllGroups() {
        List<Group> groups = groupRepository.findAllByOwnerId(getLoggedInUserId());
        return groups.stream()
                .map(GroupUtils::toGroupResponse)
                .collect(Collectors.toList());
    }

    public GroupResponse getGroupById(Long id) {
        Group group = groupRepository.findGroupWithUsersById(id,getLoggedInUserId())
                .orElseThrow(() -> new ResourceNotFoundException(101,"Group not found with id " + id));
        return toGroupResponse(group);
    }

    public GroupResponse updateGroup(Long id, GroupRequest updateRequest) {
        Group group=groupRepository.findGroupWithUsersById(id,getLoggedInUserId())
                .orElseThrow(() -> new ResourceNotFoundException(101,"Only Group owner can update/delete the group"));

        toGroup(group,updateRequest);
        groupRepository.save(group);
        return toGroupResponse(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteGroupByIdAndOwnerId(id,getLoggedInUserId());
    }

}

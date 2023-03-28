package com.todo.business;

import com.todo.a_utils.CommonUtils;
import com.todo.a_utils.GroupUtils;
import com.todo.a_utils.UserUtils;
import com.todo.dao.GroupRepository;
import com.todo.dao.UserRepository;
import com.todo.dto.GroupResponse;
import com.todo.dto.GroupUserRequest;
import com.todo.dto.UserResponse;
import com.todo.entity.Group;
import com.todo.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.todo.a_utils.CommonUtils.getLoggedInUserId;

@AllArgsConstructor
@Service
public class GroupUserService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public void addUserToGroup(GroupUserRequest groupUserRequest) {
        groupRepository.addUserToGroup(groupUserRequest.groupId(), groupUserRequest.userId());
    }

    public void removeUserFromGroup(GroupUserRequest groupUserRequest) {
        groupRepository.removeUserFromGroup(groupUserRequest.groupId(), groupUserRequest.userId());
    }

    public List<UserResponse> getUserByGroup(Long groupId) {
        List<User> users =userRepository.findAllByGroups_IdAndGroups_ownerId(groupId, getLoggedInUserId());
        return users.stream().map(UserUtils::toUserResponse).collect(Collectors.toList());
    }

    public List<GroupResponse> getGroupsByUser(Long userId) {
        List<Group> groups = groupRepository.findGroupsByOwnerIdOrUsers_Id(getLoggedInUserId(),getLoggedInUserId());
        return groups.stream().map(GroupUtils::toGroupResponse).collect(Collectors.toList());
    }
}

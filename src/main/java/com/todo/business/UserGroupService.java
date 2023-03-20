package com.todo.business;

import com.todo.a_utils.UserGroupUtils;
import com.todo.constants.Role;
import com.todo.dao.GroupRepository;
import com.todo.dao.UserGroupRoleRepository;
import com.todo.dao.UserRepository;
import com.todo.dto.UserGroupRequest;
import com.todo.entity.Group;
import com.todo.entity.User;
import com.todo.entity.UserGroupRole;
import com.todo.exceptions.ErrorCode;
import com.todo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserGroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final UserGroupRoleRepository userGroupRoleRepository;
    private final UserGroupUtils userGroupUtils;

    public void addUserToGroup(UserGroupRequest userGroupRequest) {
        User user = userRepository.findById(userGroupRequest.userId())
                .orElseThrow(()->new ResourceNotFoundException(ErrorCode.INVALID_INPUT_GIVEN,"Invalid User id"));
        Group group = groupRepository.findById(userGroupRequest.groupId())
                .orElseThrow(()->new ResourceNotFoundException(ErrorCode.INVALID_INPUT_GIVEN,"Invalid Group id"));
        List<UserGroupRole> userGroupRole = userGroupRoleRepository.findUserGroupRoleByGroup(group);
        userGroupUtils.addUserToGroup(user,group,userGroupRole);

    }

    public void removeUserFromGroup(UserGroupRequest userGroupRequest) {
        User user = userRepository.findById(userGroupRequest.userId())
                .orElseThrow(()->new ResourceNotFoundException(ErrorCode.INVALID_INPUT_GIVEN,"Invalid User id"));
        Group group = groupRepository.findById(userGroupRequest.groupId())
                .orElseThrow(()->new ResourceNotFoundException(ErrorCode.INVALID_INPUT_GIVEN,"Invalid Group id"));

        userGroupRoleRepository.deleteUserGroupRoleByGroupAndUserAndRole(group,user, Role.USER);
    }
}

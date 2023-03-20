package com.todo.a_utils;

import com.todo.constants.Role;
import com.todo.dao.UserGroupRoleRepository;
import com.todo.entity.Group;
import com.todo.entity.User;
import com.todo.entity.UserGroupRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor

public class UserGroupUtils {

    private final UserGroupRoleRepository userGroupRoleRepository;

    public  void addUserToGroup(User user, Group group, List<UserGroupRole> userGroupRoleList) {
        UserGroupRole userGroupRole = new UserGroupRole();
        userGroupRole.setUser(user);
        userGroupRole.setGroup(group);
        userGroupRole.setRole(Role.USER);
        userGroupRoleRepository.save(userGroupRole);
    }
}

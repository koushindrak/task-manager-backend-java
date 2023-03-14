package com.todo.dto;

import com.todo.entity.Group;
import com.todo.entity.Label;
import com.todo.entity.Role;
import com.todo.entity.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Long roleId;
    private Set<Long> groupIds;
    private Set<Long> labelIds;

    public User toUser() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        if (roleId != null) {
            Role role = new Role();
            role.setId(roleId);
            user.setRole(role);
        }
        if (groupIds != null) {
            Set<Group> groups = new HashSet<>();
            for (Long groupId : groupIds) {
                Group group = new Group();
                group.setId(groupId);
                groups.add(group);
            }
            user.setGroups(groups);
        }
        if (labelIds != null) {
            Set<Label> labels = new HashSet<>();
            for (Long labelId : labelIds) {
                Label label = new Label();
                label.setId(labelId);
                labels.add(label);
            }
            user.setLabels(labels);
        }
        return user;
    }

}

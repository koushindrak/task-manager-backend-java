package com.todo.entity;

import com.todo.constants.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_group_role")
@Data
public class UserGroupRole {

    @EmbeddedId
    private UserGroupRoleId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("groupId")
    private Group group;

    private Role role;

}
package com.todo.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class UserGroupRoleId implements Serializable {

    private String userId;

    private Long groupId;

}
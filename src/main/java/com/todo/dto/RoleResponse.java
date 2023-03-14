package com.todo.dto;

import com.todo.entity.Role;
import lombok.Data;

@Data
public class RoleResponse {
    private Long id;
    private String name;
    private String description;
    private String mapping;

    public RoleResponse(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
        this.mapping = role.getMapping();
    }
}

package com.todo.dto;

import com.todo.entity.Role;
import lombok.Data;

@Data
public class RoleRequest {
    private String name;
    private String description;
    private String mapping;

    public Role toRole() {
        Role role = new Role();
        role.setName(this.name);
        role.setDescription(this.description);
        role.setMapping(this.mapping);
        return role;
    }

}

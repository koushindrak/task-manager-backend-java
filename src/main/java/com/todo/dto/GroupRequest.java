package com.todo.dto;

import com.todo.entity.Group;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupRequest {
    private String name;
    private String description;
    private String status;
    private Long owner;

    public Group toGroup() {
        Group group = new Group();
        group.setName(this.name);
        group.setDescription(this.description);
        group.setStatus(this.status);
        group.setOwner(this.owner);
        return group;
    }

}
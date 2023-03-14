package com.todo.dto;

import com.todo.entity.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class GroupResponse {
    private Long id;
    private String name;
    private String description;
    private String status;
    private Long owner;
    private Set<Long> memberIds = new HashSet<>();

    public GroupResponse(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        // set other properties as needed
    }

}
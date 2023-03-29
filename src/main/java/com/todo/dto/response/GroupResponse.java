package com.todo.dto.response;

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
    private Long ownerId;
    private Set<Long> userIds = new HashSet<>();

}
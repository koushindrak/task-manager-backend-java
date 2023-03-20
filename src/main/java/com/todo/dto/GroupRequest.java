package com.todo.dto;

import com.todo.entity.Group;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public record GroupRequest(String name,String description, String status) {
    
}
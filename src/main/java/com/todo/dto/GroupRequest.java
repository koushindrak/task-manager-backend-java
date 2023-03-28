package com.todo.dto;

import com.todo.entity.Group;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public record GroupRequest(@NotBlank String name, String description,@NotBlank String status) {
    
}
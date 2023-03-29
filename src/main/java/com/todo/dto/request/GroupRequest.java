package com.todo.dto.request;

import com.todo.constants.GroupStatus;
import com.todo.entity.Group;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public record GroupRequest(
        @NotBlank(message = "Group Name can not be blank") String name,
        String description,
        @NotBlank(message = "Group Status can not be blank") GroupStatus status) {
}
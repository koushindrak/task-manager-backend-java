package com.todo.dto.request;

import com.todo.constants.GroupStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GroupRequest(
        @NotBlank(message = "Group Name can not be blank") String name,
        String description,
        @NotNull(message = "Group Status can not be blank") GroupStatus status) {
}
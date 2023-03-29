package com.todo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserGroupRequest(@NotNull(message = "User Id is mandatory") Long userId,
                               @NotNull(message = "Group Id is mandatory") Long groupId) {
}

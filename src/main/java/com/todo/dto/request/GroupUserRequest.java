package com.todo.dto.request;

import jakarta.validation.constraints.NotNull;

public record GroupUserRequest(
        @NotNull(message = "Group Id is mandatory") Long groupId,
        @NotNull(message = "User Id is mandatory") Long userId) {

}

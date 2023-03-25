package com.todo.dto;

import jakarta.validation.constraints.NotNull;

public record GroupUserRequest(@NotNull Long groupId, @NotNull Long userId) {

}

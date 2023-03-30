package com.todo.dto.response;

import jakarta.validation.constraints.NotNull;

public record GroupUserResponse(@NotNull Long groupId, @NotNull Long userId) {

}

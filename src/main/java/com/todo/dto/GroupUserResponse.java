package com.todo.dto;

import jakarta.validation.constraints.NotBlank;

public record GroupUserResponse(@NotBlank Long groupId, @NotBlank Long userId) {

}

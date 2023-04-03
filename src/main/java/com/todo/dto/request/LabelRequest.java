package com.todo.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LabelRequest(
        @NotBlank(message = "Label Name is mandatory") String name,
        String description) {
}

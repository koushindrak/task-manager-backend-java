package com.todo.dto.request;

import com.todo.entity.Task;
import com.todo.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record LabelRequest(
        @NotBlank(message = "Label Name is mandatory") String name,
        String description) {
}

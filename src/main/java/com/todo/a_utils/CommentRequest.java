package com.todo.a_utils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequest {
    @NotNull
    private Long taskId;
    @NotBlank
    private String content;
}

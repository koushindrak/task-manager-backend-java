package com.todo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequest {
    @NotNull(message = "Task Id is mandatory")
    private Long taskId;
    @NotBlank(message = "content is mandatory")
    private String content;
}

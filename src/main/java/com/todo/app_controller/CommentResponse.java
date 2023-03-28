package com.todo.app_controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CommentResponse {
    private Long commentId;
    private Long taskId;
    private String content;
    private Date time;
}

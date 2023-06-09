package com.todo.dto.response;

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
    private Long createrId;
    private String createrName;
}

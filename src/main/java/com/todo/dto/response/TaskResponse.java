package com.todo.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskResponse {

    private Long id;

    private String name;

    private String description;

    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dueDate;

    private List<TaskLabelResponse> labels;

    private List<CommentResponse> comments;

    private String priority;

    private Long groupId;

    private String groupName;

    private Long projectId;

    private String projectName;
}
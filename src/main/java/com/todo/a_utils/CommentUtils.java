package com.todo.a_utils;

import com.todo.app_controller.CommentResponse;
import com.todo.dao.TaskRepository;
import com.todo.entity.Comments;
import com.todo.entity.Task;
import com.todo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentUtils {
    private final TaskRepository taskRepository;
    public  Comments toComment(CommentRequest commentRequest) {
        Comments comments = new Comments();
        comments.setContent(commentRequest.getContent());
        Task task = taskRepository.findById(commentRequest.getTaskId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Task id"));
        comments.setTask(task);
        return comments;
    }

    public CommentResponse toCommentResponse(Comments comments) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCommentId(comments.getId());
        commentResponse.setTaskId(comments.getTask().getId());
        commentResponse.setContent(commentResponse.getContent());
        commentResponse.setTime(commentResponse.getTime());
        return commentResponse;
    }
}

package com.todo.a_utils;

import com.todo.business.UserService;
import com.todo.dao.TaskRepository;
import com.todo.dao.UserRepository;
import com.todo.dto.request.CommentRequest;
import com.todo.dto.response.CommentResponse;
import com.todo.dto.response.UserResponse;
import com.todo.entity.Comments;
import com.todo.entity.Task;
import com.todo.entity.User;
import com.todo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentUtils {
    private final TaskRepository taskRepository;
    private final UserService userService;
    public Comments toComment(CommentRequest commentRequest) {
        Comments comments = new Comments();
        comments.setContent(commentRequest.getContent());
        Task task = taskRepository.findById(commentRequest.getTaskId()).orElseThrow(() -> new ResourceNotFoundException("Invalid Task id"));
        comments.setTask(task);
        return comments;
    }

    public CommentResponse toCommentResponse(Comments comments) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCommentId(comments.getId());
        commentResponse.setTaskId(comments.getTask().getId());
        commentResponse.setContent(comments.getContent());
        commentResponse.setTime(comments.getUpdatedAt());
        if(comments.getCreatedBy() != null && !comments.getCreatedBy().equals("SYSTEM")){
            UserResponse userResponse = userService.getUserById(Long.valueOf(comments.getCreatedBy()));
            commentResponse.setCreaterId(userResponse.getId());
            commentResponse.setCreaterName(userResponse.getFirstName()+" "+userResponse.getLastName());
        }
        return commentResponse;
    }
}

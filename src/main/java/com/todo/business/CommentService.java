package com.todo.business;

import com.todo.a_utils.CommentUtils;
import com.todo.dao.CommentRepository;
import com.todo.dao.TaskRepository;
import com.todo.dto.request.CommentRequest;
import com.todo.dto.response.CommentResponse;
import com.todo.entity.Comments;
import com.todo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentUtils commentUtils;
    private final TaskRepository taskRepository;

    public CommentResponse createComment(CommentRequest commentRequest) {
        Comments comments = commentUtils.toComment(commentRequest);
        return commentUtils.toCommentResponse(commentRepository.save(comments));
    }

    public CommentResponse updateComment(Long id, CommentRequest commentRequest) {
        Comments comments = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid comment id"));
        comments.setContent(commentRequest.getContent());
        return commentUtils.toCommentResponse(commentRepository.save(comments));
    }

    public List<CommentResponse> getCommentsByTaskId(Long taskId) {
        taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Invalid Task Id"));

        List<Comments> comments = commentRepository.getCommentsByTask_Id(taskId);
        List<CommentResponse> commentResponses = new ArrayList<>();
        comments.forEach(comment -> {
            commentResponses.add(commentUtils.toCommentResponse(comment));
        });
        return commentResponses;
    }

    public void deleteComment(Long id) {
        commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Comment Id"));
        commentRepository.deleteById(id);
    }
}

package com.todo.app_controller;

import com.todo.business.CommentService;
import com.todo.dto.request.CommentRequest;
import com.todo.dto.response.CommentResponse;
import com.todo.dto.response.SuccessResponse;
import com.todo.entity.Comments;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "7-Comment Controller", description = "Used for comments cruds for tasks")
@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public SuccessResponse<CommentResponse> createComment(@Valid @RequestBody CommentRequest commentRequest) {
        return new SuccessResponse<CommentResponse>().created(commentService.createComment(commentRequest), Comments.class);
    }

    @PutMapping("/{id}")
    public SuccessResponse<CommentResponse> UpdateComment(@PathVariable Long id, @Valid @RequestBody CommentRequest commentRequest) {
        return new SuccessResponse<CommentResponse>().updated(commentService.updateComment(id, commentRequest), Comments.class);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new SuccessResponse<>().ok();
    }

}

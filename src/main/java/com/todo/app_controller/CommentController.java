package com.todo.app_controller;

import com.todo.a_utils.CommentRequest;
import com.todo.business.CommentService;
import com.todo.dto.CommentResponse;
import com.todo.dto.SuccessResponse;
import com.todo.entity.Comments;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public SuccessResponse<CommentResponse> createComment(@Valid @RequestBody CommentRequest commentRequest){
        return new SuccessResponse<CommentResponse>().created(commentService.createComment(commentRequest), Comments.class);
    }

    @PutMapping("/{id}")
    public SuccessResponse<CommentResponse> UpdateComment(@PathVariable Long id, @Valid @RequestBody  CommentRequest commentRequest){
        return new SuccessResponse<CommentResponse>().updated(commentService.updateComment(id,commentRequest),Comments.class);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return new SuccessResponse<>().ok();
    }

}

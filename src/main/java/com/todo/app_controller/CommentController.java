package com.todo.app_controller;

import com.todo.a_utils.CommentRequest;
import com.todo.business.CommentService;
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
    public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentRequest commentRequest){
        return new ResponseEntity<>(commentService.createComment(commentRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> UpdateComment(@PathVariable Long id, @Valid @RequestBody  CommentRequest commentRequest){
        return new ResponseEntity<>(commentService.updateComment(id,commentRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

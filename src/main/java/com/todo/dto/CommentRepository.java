package com.todo.dto;

import com.todo.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments,Long> {

    public List<Comments> getCommentsByTask_Id(Long taskId);
}

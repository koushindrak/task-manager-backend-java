package com.todo.dao;

import com.todo.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

    List<Label> findAllByTasks_Id(Long taskId);

    Optional<Label> findLabelByIdAndUser_Id(Long labelId, Long userId);

    List<Label> findAllByUser_Id(Long userId);

}

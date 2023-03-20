package com.todo.dao;

import com.todo.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Query(value = "insert into task_label (task_id,label_id) VALUES (:taskId,:labelId)", nativeQuery = true)
    @Transactional
    public void addLabelOnTask(@Param("taskId") Long taskId, @Param("labelId") Long labelId);

    @Modifying
    @Query(value = "DELETE FROM task_label  where task_id =:taskId and label_id =:labelId", nativeQuery = true)
    @Transactional
    void removeLabelFromTask(@Param("taskId") Long taskId, @Param("labelId") Long labelId);

    List<Task> findAllByLabels(Long labelId);
}

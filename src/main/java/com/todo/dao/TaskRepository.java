package com.todo.dao;

import com.todo.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
     List<Task> findAllByUser_Id(Long userId);
     Optional<Task> getTaskByIdAndUser_Id(Long taskId, Long userId);

    @Modifying
    @Query(value = "insert into labels_tasks (tasks_id,labels_id) VALUES (:taskId,:labelId)", nativeQuery = true)
    @Transactional
     void addLabelOnTask(@Param("taskId") Long taskId, @Param("labelId") Long labelId);

    @Modifying
    @Query(value = "DELETE FROM labels_tasks  where tasks_id =:taskId and labels_id =:labelId", nativeQuery = true)
    @Transactional
    void removeLabelFromTask(@Param("taskId") Long taskId, @Param("labelId") Long labelId);

    @Query("SELECT t FROM Task t JOIN t.labels l WHERE l.id = :labelId")
    List<Task> findTaskByLabelId(@Param("labelId") Long labelId);

    List<Task> findTaskByLabels_Id(Long labelId);

    @Query("SELECT t.user.id, t FROM Task t WHERE t.dueDate = CURRENT_DATE GROUP BY t.user.id")
    Map<Long, List<Task>> findTasksDueTodayByUser();
}

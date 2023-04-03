package com.todo.dao;

import com.todo.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Query("SELECT t FROM Task t JOIN t.project p WHERE p.id = :projectId")
    List<Task> findTasksByProject_Id(@Param("projectId") Long projectId);

    List<Task> findTaskByLabels_Id(Long labelId);

    //    @Query("SELECT " +
//            "t.user.email as user_email," +
//            " t.id as task_id," +
//            "t.name as task_name," +
//            "t.dueDate as task_due_date," +
//            " t.group.name as task_group_name, " +
//            "t.project.name as task_project_name " +
//            "FROM Task t " +
//            "WHERE DATE(t.dueDate) >=:currentDate")
//    List<Object[]> findTasksDueTodayByUser(@Param("currentDate") Date currentDate);
    @Query("SELECT " +
            " t.id as task_id," +
            "t.name as task_name," +
            "t.dueDate as task_due_date" +
            " FROM Task t order by task_due_date asc")
    List<Object[]> findTasksDueTodayByUser();

    @Query("SELECT t FROM Task t WHERE DATE(t.dueDate) > CURRENT_DATE")
    List<Task> findTasksByDueDateIsToday();
}

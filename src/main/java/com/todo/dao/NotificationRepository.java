package com.todo.dao;

import com.todo.dto.response.NotificationResponse;
import com.todo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

//    private Long id;
//    private String description;
//    private Date sentAt;
//    private String taskName;
//    private Date taskDueDate;
//    private String projectName;
//    private String groupName;

    @Query(value = """
                    select n.id as id , n.action, n.createdAt as sentAt,
                     t.due_date as taskDueDate , p.name as projectName, g.name as groupName 
                    from notifications n
                                join tasks t on n.task_id = t.id 
                                join projects p on t.project_id = p.id
                                join group_details g on t.group_id = g.id
                                where t.user_id =:userId
                    """,nativeQuery = true)
    public List<NotificationResponse> getNotificationsByUserId(@Param("userId") Long uId);

}

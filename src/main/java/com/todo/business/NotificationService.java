package com.todo.business;

import com.todo.dao.NotificationRepository;
import com.todo.dao.TaskRepository;
import com.todo.dto.response.NotificationResponse;
import com.todo.entity.Group;
import com.todo.entity.Notification;
import com.todo.entity.Project;
import com.todo.entity.Task;
import com.todo.ses.EmailTask;
import lombok.AllArgsConstructor;
import org.joda.time.Instant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.todo.a_utils.CommonUtils.getLoggedInUserId;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final TaskRepository taskRepository;

    public List<NotificationResponse> getNotificationForLoggedInUser() {
        List<Notification> notifications = notificationRepository.getNotificationsByUserId(getLoggedInUserId());
        List<NotificationResponse> responses = new ArrayList<>();

        for (Notification notification : notifications) {
            NotificationResponse notificationResponse = new NotificationResponse();
            notificationResponse.setId(notification.getId());
            notificationResponse.setSentAt(notification.getCreatedAt());
            Project project = notification.getTask().getProject();
            Group group = notification.getTask().getGroup();
            notificationResponse.setProjectName(project == null ? "-":project.getName());
            notificationResponse.setGroupName(group == null ? "-":group.getName());
            notificationResponse.setTaskName(notification.getTask().getName());
            notificationResponse.setTaskDueDate(notification.getTask().getDueDate());
            notificationResponse.setDescription(notification.getDescription());
            responses.add(notificationResponse);
        }

        return responses;
    }

    public void storeNotification(List<Task> tasks){
        tasks.forEach(task -> {
            Notification notification = new Notification();
            notification.setTask(taskRepository.findById(task.getId()).get());
            notification.setDescription(task.getDescription());
            notification.setMessage(task.getName() +" is due on "+task.getDueDate());
            notification.setUserId(task.getUser().getId());
            notificationRepository.save(notification);
        });

    }
}

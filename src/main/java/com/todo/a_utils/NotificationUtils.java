package com.todo.a_utils;

import com.todo.dto.response.NotificationResponse;
import com.todo.entity.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationUtils {
    public static List<NotificationResponse> toNotificationResponses(List<Notification> notifications) {
        List<NotificationResponse> notificationResponses = new ArrayList<>();

        notifications.forEach(notification -> {
            NotificationResponse notificationResponse = new NotificationResponse();
            notificationResponse.setId(notification.getId());
            notificationResponse.setDescription(notification.getDescription());
            notificationResponse.setSentAt(notification.getCreatedAt());
            notificationResponse.setTaskName(notification.getTask().getName());
            notificationResponse.setTaskDueDate(notification.getTask().getDueDate());

            notificationResponse.setGroupName(notification.getTask().getGroup() == null ? "-" : notification.getTask().getGroup().getName());
            notificationResponse.setProjectName(notification.getTask().getProject() == null?"-":notification.getTask().getProject().getName());
            notificationResponses.add(notificationResponse);
        });
        return notificationResponses;
    }
}

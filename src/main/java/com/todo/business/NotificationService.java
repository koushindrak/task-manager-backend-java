package com.todo.business;

import com.todo.a_utils.CommonUtils;
import com.todo.a_utils.NotificationUtils;
import com.todo.dao.NotificationRepository;
import com.todo.dto.NotificationRequest;
import com.todo.dto.NotificationResponse;
import com.todo.entity.Notification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.todo.a_utils.CommonUtils.getLoggedInUserId;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<NotificationResponse> getNotificationForLoggedInUser() {
        List<NotificationResponse> notifications = notificationRepository.getNotificationsByUserId(getLoggedInUserId());
        return notifications;
    }
}

package com.todo.app_controller;

import com.todo.business.NotificationService;
import com.todo.dto.response.NotificationResponse;
import com.todo.dto.response.SuccessResponse;
import com.todo.entity.Notification;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "8-Notification Controller", description = "User can view all notifications(new to old)")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public SuccessResponse<List<NotificationResponse>> getNotificationForLoggedInUser() {
        List<NotificationResponse> notificationResponses = notificationService.getNotificationForLoggedInUser();
        return new SuccessResponse().retrieved(notificationResponses, Notification.class);
    }
}

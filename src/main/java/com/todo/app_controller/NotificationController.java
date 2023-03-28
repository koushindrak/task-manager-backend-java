package com.todo.app_controller;

import com.todo.business.NotificationService;
import com.todo.dto.NotificationRequest;
import com.todo.dto.NotificationResponse;
import com.todo.dto.SuccessResponse;
import com.todo.entity.Notification;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public SuccessResponse<List<NotificationResponse>> getNotificationForLoggedInUser(){
       List<NotificationResponse> notificationResponses = notificationService.getNotificationForLoggedInUser();
       return new SuccessResponse().retrieved(notificationResponses, Notification.class);
    }
}

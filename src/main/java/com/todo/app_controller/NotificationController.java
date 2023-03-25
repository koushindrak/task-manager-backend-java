package com.todo.app_controller;

import com.todo.business.NotificationService;
import com.todo.dto.NotificationRequest;
import com.todo.dto.NotificationResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public ResponseEntity<NotificationResponse> sendNotification(@Valid @RequestBody NotificationRequest notificationRequest){
       NotificationResponse notificationResponse = notificationService.sendNotification(notificationRequest);
       return new ResponseEntity<NotificationResponse>(notificationResponse, HttpStatus.OK);
    }
}

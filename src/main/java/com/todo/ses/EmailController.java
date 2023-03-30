package com.todo.ses;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EmailController {

    private final AWSEmailService emailService;

    private final JavaMailService javaMailService;

    private final NotificationScheduler notificationScheduler;

    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailRequest emailRequest) throws Exception {
//        emailService.sendEmail(emailRequest);
//        javaMailService.sendSampleMail(emailRequest);
        notificationScheduler.runDailyJob();
    }

}

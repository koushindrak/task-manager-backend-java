package com.todo.ses;

import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
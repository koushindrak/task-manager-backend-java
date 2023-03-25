package com.todo.ses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmailController {
    
    @Autowired
    private MailSender mailSender;
    
    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailRequest emailRequest) throws Exception {
        mailSender.sendMail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
    }
    
}

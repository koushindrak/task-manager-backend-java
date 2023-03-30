package com.todo.ses;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class AWSEmailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private AWSMailSender awsMailSender;

    public void sendEmail(EmailRequest emailRequest) throws Exception {

        List<EmailTask> tasks = new ArrayList<>();
        tasks.add(new EmailTask("Task 1", new LocalDate(), "Group 1", "Project 1"));
        tasks.add(new EmailTask("Task 2", new LocalDate(), "Group 2", "Project 2"));

        // Create a Thymeleaf context with the tasks list as a variable
        Context context = new Context();
        context.setVariable("tasks", tasks);

        // Render the Thymeleaf template to a string
        String htmlContent = templateEngine.process("email-template", context);

        awsMailSender.sendMail(emailRequest.getTo(), "AWS MAIL SERVICE--" + emailRequest.getSubject(), htmlContent);
    }
}

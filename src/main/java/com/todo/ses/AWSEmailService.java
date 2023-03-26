package com.todo.ses;

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

        List<EmailTasks> tasks = new ArrayList<>();
        tasks.add(new EmailTasks("Task 1", "2023-03-31", "Description 1", "Group 1", "Project 1"));
        tasks.add(new EmailTasks("Task 2", "2023-04-15", "Description 2", "Group 2", "Project 2"));
        tasks.add(new EmailTasks("Task 3", "2023-04-30", "Description 3", "Group 3", "Project 3"));

        // Create a Thymeleaf context with the tasks list as a variable
        Context context = new Context();
        context.setVariable("tasks", tasks);

        // Render the Thymeleaf template to a string
        String htmlContent = templateEngine.process("email-template", context);

        awsMailSender.sendMail(emailRequest.getTo(), "AWS MAIL SERVICE--" + emailRequest.getSubject(), htmlContent);
    }
}

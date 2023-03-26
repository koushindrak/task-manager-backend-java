package com.todo.ses;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class JavaMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;


    public void sendEmail(EmailRequest emailRequest) throws Exception {
        // Create a Thymeleaf context with the tasks list as a variable

        List<EmailTasks> tasks = new ArrayList<>();
        tasks.add(new EmailTasks("Task 1", "2023-03-31", "Description 1", "Group 1", "Project 1"));
        tasks.add(new EmailTasks("Task 2", "2023-04-15", "Description 2", "Group 2", "Project 2"));
        tasks.add(new EmailTasks("Task 3", "2023-04-30", "Description 3", "Group 3", "Project 3"));

        Context context = new Context();
        context.setVariable("tasks", tasks);

        // Render the Thymeleaf template to a string
        String htmlContent = templateEngine.process("email-template", context);

        //Create a MimeMessage with the HTML content
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("koushindrakumar26@gmail.com");
        helper.setTo(emailRequest.getTo());
        helper.setSubject("JAVA MAIL SERVICE-Task List");
        helper.setText(htmlContent, true);

        // Send the email
        javaMailSender.send(message);

    }
}


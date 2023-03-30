package com.todo.ses;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class JavaMailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final JavaMailConfiguration javaMailConfiguration;

    public void sendSampleMail(EmailRequest emailRequest) throws Exception {
        // Create a Thymeleaf context with the tasks list as a variable

        List<EmailTask> tasks = new ArrayList<>();
        tasks.add(new EmailTask("Task 1", new LocalDate(), "Group 1", "Project 1"));
        tasks.add(new EmailTask("Task 2", new LocalDate(), "Group 2", "Project 2"));
        tasks.add(new EmailTask("Task 3", new LocalDate(), "Group 3", "Project 3"));

        Context context = new Context();
        context.setVariable("tasks", tasks);

        // Render the Thymeleaf template to a string
        String htmlContent = templateEngine.process("email-template", context);

        //Create a MimeMessage with the HTML content
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(javaMailConfiguration.getUsername());
        helper.setTo(emailRequest.getTo());
        helper.setSubject("JAVA MAIL SERVICE-Task List");
        helper.setText(htmlContent, true);

        // Send the email
        javaMailSender.send(message);

    }

    public void sendTodaysTaskList(String email, List<EmailTask> tasks) throws Exception {
        // Create a Thymeleaf context with the tasks list as a variable


        Context context = new Context();
        context.setVariable("tasks", tasks);

        // Render the Thymeleaf template to a string
        String htmlContent = templateEngine.process("email-template", context);

        //Create a MimeMessage with the HTML content
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(javaMailConfiguration.getUsername());
        helper.setTo(email);
        helper.setSubject("JAVA MAIL SERVICE-Task List");
        helper.setText(htmlContent, true);

        // Send the email
        javaMailSender.send(message);

    }
}


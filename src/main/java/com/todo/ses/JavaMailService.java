package com.todo.ses;

import com.todo.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
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

    public void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(javaMailConfiguration.getUsername(), "Koushindra Kumar");
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstName() + " " + user.getLastName());
        String verifyURL = siteURL + "/api/v1/auth/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

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


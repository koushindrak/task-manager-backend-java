package com.todo.ses;

import com.todo.dao.TaskRepository;
import com.todo.entity.Task;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationScheduler {

    private final TaskRepository taskRepository;
    private final JavaMailService javaMailService;

    @Scheduled(cron = "0 0 * * * *")
    public void runDailyJob() {
        log.info("******************************************************");
        log.info("==================DAILY EMAIL JOB STARTED ==============");

        List<Task> todaysTasks = taskRepository.findTasksByDueDateIsToday();

        Map<String, List<EmailTask>> tasksByUser = todaysTasks.stream()
                .map(task -> new EmailTask(
                        task.getUser().getEmail(),
                        task.getId(),
                        task.getName(),
                        LocalDate.fromDateFields(task.getDueDate()),
                        task.getGroup() == null ? "-" : task.getGroup().getName(),
                        task.getProject() == null ? "-" : task.getProject().getName()))
                .collect(Collectors.groupingBy(
                        EmailTask::getEmail,
                        LinkedHashMap::new,
                        Collectors.toList()));

        tasksByUser.forEach((userId, userTasks) -> {
            userTasks.sort(Comparator.comparing(EmailTask::getTaskDueDate));
        });

        tasksByUser.forEach((email, tasks) -> {

            List<EmailTask> emailTasks = new ArrayList<>();
            tasks.forEach(task -> {
                EmailTask emailTask = new EmailTask(task.getTaskName(), task.getTaskDueDate(),
                        task.getGroupName() == null ? "_" : task.getGroupName(),
                        task.getProjectName() == null ? "_" : task.getProjectName());
                emailTasks.add(emailTask);
            });

            try {
                javaMailService.sendTodaysTaskList(email, emailTasks);
                log.info("============= DAILY TASK'S MAIL SENT TO USER - " + email + "===================");
            } catch (Exception e) {
                log.info("Exception Occured while Sending daily mail to user - " + email + "===================");
                throw new RuntimeException(e);
            }

        });
        log.info("------------------- DAILY EMAIL JOB FINISHED -----------------------");
    }
}
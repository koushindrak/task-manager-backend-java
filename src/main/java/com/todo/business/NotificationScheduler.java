package com.todo.business;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationScheduler {

    @Scheduled(cron = "0 0 6 * * ?")
    public void runDailyJob() {

    }
}
//package com.todo.ses;
//
//import com.todo.dao.TaskRepository;
//import com.todo.entity.Task;
//import lombok.AllArgsConstructor;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.Map;
//
//@Component
//@AllArgsConstructor
//public class NotificationScheduler {
//
//    private final TaskRepository taskRepository;
//
////    @Scheduled(cron = "0 0 6 * * ?")
//    public void runDailyJob() {
//        //find today's due tasks
//       Map<Long, List<Task>> tasksByUser = taskRepository.findTasksDueTodayByUser();
//
//        tasksByUser.forEach((userId, userTasks) -> {
//            userTasks.sort(Comparator.comparing(Task::getDueDate));
//        });
//
//        tasksByUser.forEach((k,v)->{
//
//        });
//    }
//}
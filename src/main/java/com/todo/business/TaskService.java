package com.todo.business;

import com.todo.dao.LabelRepository;
import com.todo.dao.PriorityRepository;
import com.todo.dao.TaskRepository;
import com.todo.dao.UserRepository;
import com.todo.dto.TaskRequest;
import com.todo.dto.TaskResponse;
import com.todo.entity.Label;
import com.todo.entity.Priority;
import com.todo.entity.Task;
import com.todo.entity.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final LabelRepository labelRepository;

    private final PriorityRepository priorityRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, LabelRepository labelRepository, PriorityRepository priorityRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.labelRepository = labelRepository;
        this.priorityRepository = priorityRepository;
    }

    public Task createTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(Date.from(taskRequest.getDueDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        if (taskRequest.getUserId() != null) {
            User user = userRepository.findById(taskRequest.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + taskRequest.getUserId()));
            task.setUser(user);
        }
        if (taskRequest.getLabelId() != null) {
            Label label = labelRepository.findById(taskRequest.getLabelId())
                    .orElseThrow(() -> new EntityNotFoundException("Label not found with id: " + taskRequest.getLabelId()));
            task.setLabel(label);
        }
        if (taskRequest.getPriorityId() != null) {
            Priority priority = priorityRepository.findById(taskRequest.getPriorityId())
                    .orElseThrow(() -> new EntityNotFoundException("Priority not found with id: " + taskRequest.getPriorityId()));
            task.setPriority(priority);
        }
        return taskRepository.save(task);
    }
    public List<TaskResponse> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> new TaskResponse(task))
                .collect(Collectors.toList());
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        return new TaskResponse(task);
    }



    public Task updateTask(Long id, TaskRequest taskRequest) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        existingTask.setName(taskRequest.getName());
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setDueDate(Date.from(taskRequest.getDueDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        if (taskRequest.getUserId() != null) {
            User user = userRepository.findById(taskRequest.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + taskRequest.getUserId()));
            existingTask.setUser(user);
        } else {
            existingTask.setUser(null);
        }
        if (taskRequest.getLabelId() != null) {
            Label label = labelRepository.findById(taskRequest.getLabelId())
                    .orElseThrow(() -> new EntityNotFoundException("Label not found with id: " + taskRequest.getLabelId()));
            existingTask.setLabel(label);
        } else {
            existingTask.setLabel(null);
        }
        if (taskRequest.getPriorityId() != null) {
            Priority priority = priorityRepository.findById(taskRequest.getPriorityId())
                    .orElseThrow(() -> new EntityNotFoundException("Priority not found with id: " + taskRequest.getPriorityId()));
            existingTask.setPriority(priority);
        } else {
            existingTask.setPriority(null);
        }
        return taskRepository.save(existingTask);
    }

    public TaskResponse deleteTask(Long id) {
        TaskResponse task = getTaskById(id);
        taskRepository.deleteById(id);
        return task;
    }

}

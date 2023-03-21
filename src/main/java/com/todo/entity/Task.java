package com.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todo.constants.Priority;
import com.todo.constants.TaskFrequency;
import com.todo.constants.TaskStatus;
import com.todo.constants.TaskType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Data
public class Task extends ParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "due_date")
    private Date dueDate;
//TODO:- enhancement for recurring tasks
//    private TaskType taskType;
//
//    private TaskFrequency taskFrequency;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToMany
    private Set<Label> labels;

    @Column(name = "priority")
    private Priority priority;

}

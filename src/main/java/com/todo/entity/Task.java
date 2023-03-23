package com.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(length = 50,nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "due_date")
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",columnDefinition = "ENUM('TODO', 'INPROGRESS','DONE')")
    private TaskStatus taskStatus;

    @Column(name = "priority",columnDefinition = "ENUM('HIGH', 'LOW','MEDIUM')")
    private Priority priority;


    @ManyToOne
//    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToMany(mappedBy = "tasks", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("tasks")
    private Set<Label> labels;

    @ManyToOne
    private Group group;

    @OneToMany(mappedBy = "task")
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "task")
    private Set<Comments> comments;

    @ManyToOne
    private Project project;
}








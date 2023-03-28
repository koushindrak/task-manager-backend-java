package com.todo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.sql.Timestamp;

@Entity
@Table(name = "notifications")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false,updatable = false)
    private String description;

    @Column(name="message" , length = 65535, columnDefinition="TEXT",nullable = false,updatable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}

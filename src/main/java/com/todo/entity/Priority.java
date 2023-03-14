package com.todo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "priority")
@Data
public class Priority extends ParentEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String description;
    
    private int value;
    
    private String status;

    @OneToMany
    private Set<Task> task;
    // getters and setters
}

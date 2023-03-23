package com.todo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "labels")
@Data
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20,nullable = false)
    private String name;

    @Column(length = 100)
    private String description;

    @ManyToOne
//    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToMany
    @JsonIgnoreProperties("labels")
    private Set<Task> tasks;

}

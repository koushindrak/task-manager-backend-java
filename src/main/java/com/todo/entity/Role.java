package com.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todo.context.ExecutionContext;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Entity(name = "Role")
@Table(name = "role")
@Data
public class Role extends ParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Lob
    private String mapping;

    @JsonIgnore
    @OneToMany(mappedBy = "role", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<User> users;
}

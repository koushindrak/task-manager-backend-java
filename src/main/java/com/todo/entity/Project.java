package com.todo.entity;

import com.todo.constants.ProjectStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "projects")
@Data
public class Project extends ParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",columnDefinition = "ENUM('ACTIVE', 'INACTIVE')")
    private ProjectStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "project")
    private Set<Task> tasks;
}

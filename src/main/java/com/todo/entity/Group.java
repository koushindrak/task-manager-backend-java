package com.todo.entity;

import com.todo.constants.GroupStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "group_details")
@Data
public class Group extends ParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50,nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",columnDefinition = "ENUM('ACTIVE', 'INACTIVE')")
    private GroupStatus status;

    private Long ownerId;

    // mappings-- Group-User(members) M2M, Group-Task- 12M
    @ManyToMany(mappedBy = "groups") // ignore tables starting with groups i.e groups_members, just create members_groups
    private Set<User> members = new HashSet<>();


    @OneToMany(mappedBy = "group")
    private Set<Task> tasks;
}






















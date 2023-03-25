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
@Setter
@Getter
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

    @Column(name = "owner_id")
    private Long ownerId;

    // mappings-- Group-User(users) M2M, Group-Task- 12M
//    @ManyToMany(mappedBy = "groups") // ignore tables starting with groups i.e groups_users, just create users_groups

    @ManyToMany(mappedBy = "groups",fetch = FetchType.EAGER)
//    @JoinTable(name = "groups_users", joinColumns =
//    @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "group")
    private Set<Task> tasks;


}






















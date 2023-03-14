package com.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todo.constants.CommonConstants;
import com.todo.context.ExecutionContext;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "User")
@Table(name = "user")
@Data
public class User extends ParentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String lastName = CommonConstants.EMPTY_STRING;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    //relationships
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private LoginDetails loginDetails;

    @ManyToMany(mappedBy = "members")
    private Set<Group> groups = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserGroupRole> groupRoles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Label> labels = new HashSet<>();
}
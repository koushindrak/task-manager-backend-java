package com.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todo.constants.CommonConstants;
import com.todo.context.ExecutionContext;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity(name = "User")
@Table(name = "user")
public class User extends ParentEntity {
    @Id
    private String id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName = CommonConstants.EMPTY_STRING;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "role_id")
    private Role userRole;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private LoginDetails loginDetails;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_phone_numbers", joinColumns = {@JoinColumn(name = "user_id")})
    private Set<String> phoneNumbers;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public LoginDetails getLoginDetails() {
        return loginDetails;
    }

    public void setLoginDetails(LoginDetails loginDetails) {
        this.loginDetails = loginDetails;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @PrePersist
    public void prePersist() {
        this.setCreatedBy((Objects.isNull(ExecutionContext.get()) || Objects.isNull(ExecutionContext.get().getUsercontext().getId())) ? "SYSTEM" : ExecutionContext.get().getUsercontext().getId());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedBy((Objects.isNull(ExecutionContext.get()) || Objects.isNull(ExecutionContext.get().getUsercontext().getId())) ? "SYSTEM" : ExecutionContext.get().getUsercontext().getId());
    }

}

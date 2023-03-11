package com.todo.entity;

import jakarta.persistence.*;

@Entity(name = "LoginDetails")
@Table(name = "login_details")
public class LoginDetails extends ParentEntity {
    @Id
    @Column
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    @Column(name = "token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

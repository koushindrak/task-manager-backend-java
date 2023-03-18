//package com.todo.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity(name = "LoginDetails")
//@Table(name = "login_details")
//@Data
//public class LoginDetails extends ParentEntity {
//    @Id
//    @Column
//    private String id;
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @Lob
//    @Column(name = "token")
//    private String token;
//
//}

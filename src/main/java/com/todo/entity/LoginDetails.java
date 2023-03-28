package com.todo.entity;

import com.todo.constants.CommonConstants;
import com.todo.context.ExecutionContext;
import com.todo.security.token.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login_details")
public class LoginDetails{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @Column(unique = true,nullable = false,updatable = false)
  public String token;

  @Enumerated(EnumType.STRING)
  @Column(name = "token_type",columnDefinition = "ENUM('BEARER')",nullable = false,updatable = false)
  public TokenType tokenType = TokenType.BEARER;

  @Column(name = "is_revoked")
  public boolean revoked;

  @Column(name = "is_expired")
  public boolean expired;

  @ManyToOne
  @JoinColumn(name = "user_id",nullable = false,updatable = false) // this will add user_id column in LoginDetails table
  public User user;

  @Column(name = "logged_in_time", nullable = false, updatable = false)
  @CreationTimestamp
  private Timestamp loggedInAt;

  @Column(name = "logged_out_time")
  @UpdateTimestamp
  private Timestamp loggedOutAt;

}

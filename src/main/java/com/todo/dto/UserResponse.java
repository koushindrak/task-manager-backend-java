
package com.todo.dto;


import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String roleId;
    private String phoneNumber;

}

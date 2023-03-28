package com.todo.dto;

import com.todo.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserRequest {

    @NotBlank(message = "First Name can not be blank")
    private String firstName;
    @NotBlank(message = "First Name can not be blank")
    private String lastName;
    @NotBlank(message = "email can not be blank")
    private String email;
    @NotBlank(message = "Password can not be blank")
    private String password;
    private String phoneNumber;

    public User toUser() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

}

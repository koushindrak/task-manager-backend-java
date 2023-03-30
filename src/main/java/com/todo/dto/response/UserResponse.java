package com.todo.dto.response;

import com.todo.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public UserResponse() {
    }

    public UserResponse(User user) {
        BeanUtils.copyProperties(user, this);
    }
}

package com.todo.dto.response;

import com.todo.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date createdAt;

    public UserResponse() {
    }

    public UserResponse(User user) {
        BeanUtils.copyProperties(user, this);
    }
}

package com.todo.a_utils;

import com.todo.dto.response.UserResponse;
import com.todo.entity.User;
import org.springframework.beans.BeanUtils;

public class UserUtils {

    public static UserResponse toUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user,userResponse);
        return userResponse;
    }
}

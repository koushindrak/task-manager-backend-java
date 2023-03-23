package com.todo.a_utils;

import com.todo.constants.Role;
import com.todo.context.ExecutionContext;
import com.todo.dao.UserRepository;
import com.todo.entity.User;
import com.todo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CommonUtils {

    private final UserRepository userRepository;

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static Long getCurrentTimeInMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static Role getRoleOfLoggedInUser() {
        return ExecutionContext.get().getUsercontext().role();
    }

    public static Long getLoggedInUserId() {
        return ExecutionContext.get().getUsercontext().id();
    }

    public User getCurrentUser(){
        User user= userRepository.findByEmail(ExecutionContext.get().getUsercontext().email()).get();
//        user.setGroupRoles(null);
        return user;
    }
}

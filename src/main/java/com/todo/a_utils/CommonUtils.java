package com.todo.a_utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.constants.Role;
import com.todo.context.ExecutionContext;
import com.todo.dao.UserRepository;
import com.todo.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class CommonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();
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

    public static void printJson(Object obj) {
//        try {
//            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//            String json = objectMapper.writeValueAsString(obj);
//            log.info("=========="+obj.getClass()+"==========");
//            log.info(json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }

    public User getCurrentUser() {
        User user = userRepository.findByEmail(ExecutionContext.get().getUsercontext().email()).get();
//        user.setGroupRoles(null);
        return user;
    }
}

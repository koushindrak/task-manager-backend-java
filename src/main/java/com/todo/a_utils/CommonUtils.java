package com.todo.a_utils;

import com.todo.context.ExecutionContext;

import java.util.Calendar;
import java.util.UUID;

public class CommonUtils {
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static Long getCurrentTimeInMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static Long getRoleIdOfLoggedInUser() {
        return ExecutionContext.get().getUsercontext().roleId();
    }

    public static Long getUserIdOfLoggedInUser() {
        return ExecutionContext.get().getUsercontext().id();
    }
}

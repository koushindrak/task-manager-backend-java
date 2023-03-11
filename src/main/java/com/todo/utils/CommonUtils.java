package com.todo.utils;

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

    public static String getRoleIdOfLoggedInUser() {
        return ExecutionContext.get().getUsercontext().getRoleId();
    }

    public static String getUserIdOfLoggedInUser() {
        return ExecutionContext.get().getUsercontext().getId();
    }
}

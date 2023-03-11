package com.todo.exceptions;

import org.springframework.stereotype.Component;

@Component
public class CustomExceptionSchema {
    private int code;
    private String message;
    private Object stackTace;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getStackTace() {
        return stackTace;
    }

    public void setStackTace(Object stackTace) {
        this.stackTace = stackTace;
    }
}

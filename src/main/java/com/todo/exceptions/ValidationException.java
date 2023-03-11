package com.todo.exceptions;

public class ValidationException extends ApiException {
    public ValidationException(int code, String message) {
        super(code,message);
    }
}

package com.todo.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
    private int code;
    private String message;
    private Object stackTace;


    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}

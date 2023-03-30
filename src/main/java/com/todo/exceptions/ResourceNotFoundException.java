package com.todo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private Integer code;
    private String message;
    private Object stackTace;

    public ResourceNotFoundException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResourceNotFoundException(String message) {
        this.message = message;
    }
}

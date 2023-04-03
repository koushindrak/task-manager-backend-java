package com.todo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceAlreadyExistsException extends RuntimeException {
    private String message;
}

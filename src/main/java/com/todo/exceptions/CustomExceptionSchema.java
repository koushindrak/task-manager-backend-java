package com.todo.exceptions;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CustomExceptionSchema {
    private int code;
    private String message;
    private Object stackTace;

}

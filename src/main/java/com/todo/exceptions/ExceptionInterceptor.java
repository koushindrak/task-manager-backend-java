package com.todo.exceptions;

import com.todo.dto.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
@AllArgsConstructor
public class ExceptionInterceptor {

    private final ErrorResponse errorResponse;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class,
            DataIntegrityViolationException.class})
    @ResponseBody
    public final ErrorResponse handleDuplicateException(SQLIntegrityConstraintViolationException ex) {
        ex.printStackTrace();
      return errorResponse.failure(ErrorCode.DUPLICATE_COLUMN_EXCEPTION,HttpStatus.BAD_REQUEST, ex);
    }
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseBody
    public final ErrorResponse handleValidationExceptions(ResourceNotFoundException ex) {
        ex.printStackTrace();
        return errorResponse.failure(ErrorCode.INVALID_INPUT_GIVEN,HttpStatus.BAD_REQUEST,ex);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ValidationException.class})
    @ResponseBody
    public final ErrorResponse handleValidationExceptions(ValidationException ex) {
        ex.printStackTrace();
        return errorResponse.failure(ErrorCode.INVALID_INPUT_GIVEN,HttpStatus.BAD_REQUEST,ex);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ServletRequestBindingException.class})
    @ResponseBody
    public final ErrorResponse exception(ServletRequestBindingException ex) {
        ex.printStackTrace();
        return errorResponse.failure(ErrorCode.INVALID_SERVELET_REQUEST,HttpStatus.BAD_REQUEST,ex);
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseBody
    public final ErrorResponse exception(AuthenticationException ex) {
        ex.printStackTrace();
        return errorResponse.failure(ErrorCode.UNAUTHORIZED,HttpStatus.BAD_REQUEST,ex);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public final ErrorResponse exception(Exception ex) {
        ex.printStackTrace();
        return errorResponse.failure(ErrorCode.UNHANDLED_EXCEPTION,HttpStatus.BAD_REQUEST, ex);
    }
}

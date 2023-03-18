package com.todo.exceptions;

import com.todo.dto.ResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ExceptionInterceptor {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class,
            DataIntegrityViolationException.class})
    @ResponseBody
    public final ResponseDTO.Error handleDuplicateException(SQLIntegrityConstraintViolationException ex) {
        ex.printStackTrace();
      return new ResponseDTO().failure(ErrorCode.DUPLICATE_COLUMN_EXCEPTION,HttpStatus.BAD_REQUEST, ex);
    }
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseBody
    public final ResponseDTO handleValidationExceptions(ResourceNotFoundException ex) {
        return new ResponseDTO();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ValidationException.class})
    @ResponseBody
    public final ResponseDTO handleValidationExceptions(ValidationException ex) {
        return new ResponseDTO();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ServletRequestBindingException.class})
    @ResponseBody
    public final ResponseDTO exception(ServletRequestBindingException ex) {
        return new ResponseDTO();
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseBody
    public final ResponseDTO exception(AuthenticationException authEx) {
        return new ResponseDTO();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public final ResponseDTO.Error exception(Exception ex) {
        ex.printStackTrace();
        return new ResponseDTO().failure(ErrorCode.UNHANDLED_EXCEPTION,HttpStatus.BAD_REQUEST, ex);
    }
}

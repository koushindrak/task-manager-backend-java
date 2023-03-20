package com.todo.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class ResponseDTO<T> {

    @Data
    public class SuccessResponse {
        private String message;
        private T data;
    }

    @Data
    public  class Error {
        private Integer errorCode;
        private Integer httpStatus;
        private String errorMessage;
        private List<String> cause;
        private String errorLogs;
    }

    public SuccessResponse created(T data, Class entity) {
        String action = " Created ";
        return getSuccessResponse(data, entity, action);
    }

    public SuccessResponse retrieved(T data, Class entity) {
        String action = " Retrieved ";
        return getSuccessResponse(data, entity, action);
    }

    public SuccessResponse updated(T data, Class entity) {
        String action = " Updated ";
        return getSuccessResponse(data, entity, action);
    }

    public SuccessResponse deleted(T data, Class entity) {
        String action = " Deleted ";
        return getSuccessResponse(data, entity, action);
    }


    private SuccessResponse getSuccessResponse(T data, Class entity, String action) {
        SuccessResponse success = new SuccessResponse();
        success.setData(data);
        success.setMessage(entity.getSimpleName().toUpperCase() + action + "Successfully");
        return success;
    }

    public Error failure(int errorCode, HttpStatus httpStatus, Exception e) {
        Error error = new Error();
        error.setErrorCode(errorCode);
        error.setErrorMessage(e.getMessage());
        error.setHttpStatus(httpStatus.value());

        List<String> causes = new ArrayList<>();
        int counter = 0;
        Throwable throwable = e.getCause();
        while (throwable != null && counter < 5) {
            causes.add(throwable.getMessage());
            throwable = throwable.getCause();
            counter++;
        }
        error.setCause(causes);
        error.setErrorLogs(Arrays.toString(e.getStackTrace()));
        return error;
    }

}

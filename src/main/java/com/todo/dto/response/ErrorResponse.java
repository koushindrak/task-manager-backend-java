package com.todo.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@Component
public class ErrorResponse<T> {
    private Integer errorCode;
    private Integer httpStatus;
    private String errorMessage;
    private List<String> cause;
    private String errorLogs;

    public ErrorResponse failure(int errorCode, HttpStatus httpStatus, Exception e) {
        ErrorResponse error = new ErrorResponse();
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

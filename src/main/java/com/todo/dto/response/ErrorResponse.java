//package com.todo.dto.response;
//
//import com.todo.exceptions.ErrorCode;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//@Setter
//@Getter
//@Component
//public class ErrorResponse<T> {
//    private String errorCode;
//    private String errorMessage;
//    private List<String> cause;
//    private String errorLogs;
//
//    public ErrorResponse failure(Map<String,String> validationErrors){
//        ErrorResponse error = new ErrorResponse();
//        error.setErrorCode("500");
//        error.setErrorMessage(validationErrors.toString());
//        return error;
//    }
//    public ErrorResponse failure(ErrorCode errorCode, HttpStatus httpStatus, Exception e) {
//        ErrorResponse error = new ErrorResponse();
//        error.setErrorCode("500");
//        error.setErrorMessage(e.getMessage());
//
//        List<String> causes = new ArrayList<>();
//        int counter = 0;
//        Throwable throwable = e.getCause();
//        while (throwable != null && counter < 5) {
//            causes.add(throwable.getMessage());
//            throwable = throwable.getCause();
//            counter++;
//        }
//        error.setCause(causes);
//        error.setErrorLogs(Arrays.toString(e.getStackTrace()));
//        return error;
//    }
//}

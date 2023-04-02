package com.todo.exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class ExceptionInterceptor {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class,
            DataIntegrityViolationException.class})
    @ResponseBody
    public final ProblemDetail handleDuplicateException(SQLIntegrityConstraintViolationException ex) {
        ex.printStackTrace();
        ProblemDetail problemDetail = new ProblemDetailBuilder()
                .withHttpStatus(HttpStatus.BAD_REQUEST)
                .withErrorCategoryAndDetails(ErrorCategory.DATABASE_ERROR, ErrorCode.DUPLICATE_DB_FIELD_VALUE, ErrorMessage.DB_CONSTAINT_ERROR_MSG, ex.getMessage())
                .build();
        return problemDetail;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseBody
    public final ProblemDetail handleValidationExceptions(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = new ProblemDetailBuilder()
                .withHttpStatus(HttpStatus.BAD_REQUEST)
                .withErrorCategoryAndDetails(ErrorCategory.CLIENT_SIDE_ERROR, ErrorCode.RESOURCE_WITH_GIVEN_ID_DOES_NOT_EXISTS,
                        ex.getMessage(), ErrorMessage.RESOUCE_DOES_NOT_EXISTS)
                .build();
        return problemDetail;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ValidationException.class})
    @ResponseBody
    public final ProblemDetail handleValidationExceptions(ValidationException ex) {
        ex.printStackTrace();
        ProblemDetail problemDetail = new ProblemDetailBuilder()
                .withHttpStatus(HttpStatus.BAD_REQUEST)
                .withErrorCategoryAndDetails(ErrorCategory.CLIENT_SIDE_ERROR, ErrorCode.RESOURCE_WITH_GIVEN_ID_DOES_NOT_EXISTS,
                        ex.getMessage(), ErrorMessage.RESOUCE_DOES_NOT_EXISTS)
                .build();
        return problemDetail;
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseBody
    public final ProblemDetail exception(AuthenticationException ex) {
        ProblemDetail problemDetail = new ProblemDetailBuilder()
                .withHttpStatus(HttpStatus.UNAUTHORIZED)
                .withErrorCategoryAndDetails(ErrorCategory.CREDENTIALS_ERROR, ErrorCode.UNAUTHORIZED_ACCESS,
                        ex.getMessage(), ErrorMessage.MISSING_OR_INVALID_CREDS)
                .build();
        return problemDetail;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public final ProblemDetail handleInvalidRequestIssue1(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        String displayError = errors.entrySet().stream().findFirst().get().getValue();
        ProblemDetail problemDetail = new ProblemDetailBuilder()
                .withHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .withErrorCategoryAndDetails(ErrorCategory.CLIENT_SIDE_ERROR, ErrorCode.FIELD_ERRORS, displayError, errors.toString())
                .build();
        return problemDetail;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ResourceAlreadyExistsException.class})
    @ResponseBody
    public final ProblemDetail handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        log.info("\n\n INSIDE handleResourceAlreadyExistsException METHOD ==>", ex);
        ProblemDetail problemDetail = new ProblemDetailBuilder()
                .withHttpStatus(HttpStatus.BAD_REQUEST)
                .withErrorCategoryAndDetails(ErrorCategory.CLIENT_SIDE_ERROR, ErrorCode.RESOURCE_ALREADY_EXISTS, ex.getMessage(), "N/A")
                .build();
        return problemDetail;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ApiException.class})
    @ResponseBody
    public final ProblemDetail handleApiException(ApiException ex) {
        log.info("\n\n INSIDE handleApiException METHOD ==>", ex);
        ProblemDetail problemDetail = new ProblemDetailBuilder()
                .withHttpStatus(HttpStatus.BAD_REQUEST)
                .withErrorCategoryAndDetails(ErrorCategory.CLIENT_SIDE_ERROR, ErrorCode.INVALID_INPUT_GIVEN, ex.getMessage(), "N/A")
                .build();
        return problemDetail;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public final ProblemDetail exception(Exception ex) {
        log.info("\n\n INSIDE EXCEPTION METHOD ==>", ex);
        ProblemDetail problemDetail = new ProblemDetailBuilder()
                .withHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .withErrorCategoryAndDetails(ErrorCategory.SERVER_SIDE_ERROR, ErrorCode.UNHANDLED_EXCEPTION, ErrorMessage.SOMETHING_WENT_WRONG, ex.getMessage())
                .build();
        return problemDetail;
    }
}

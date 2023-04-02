package com.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProblemDetailBuilder {
    private HttpStatus httpStatus;
    private String title;
    private String apiPath;
    private ErrorCategory errorCategory;
    private ErrorCode errorCode;
    private String displayError;
    private String details;

    public ProblemDetailBuilder withHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public ProblemDetailBuilder withTitleAndApiPath(String title, String apiPath) {
        this.title = title;
        this.apiPath = apiPath;
        return this;
    }

    public ProblemDetailBuilder withErrorCategoryAndDetails(ErrorCategory errorCategory, ErrorCode errorCode, String displayError, String errorDetails) {
        this.errorCategory = errorCategory;
        this.errorCode = errorCode;
        this.displayError = displayError;
        this.details = errorDetails;
        return this;
    }

    public ProblemDetail build() {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, displayError);
        if (title != null)
            problemDetail.setTitle(title);

        problemDetail.setDetail(details);

        if (apiPath != null)
            problemDetail.setInstance(URI.create(apiPath));

        problemDetail.setProperty("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        problemDetail.setProperty("errorCategory", errorCategory);
        problemDetail.setProperty("errorCode", errorCode);
        problemDetail.setProperty("displayError", displayError);
        return problemDetail;
    }
}

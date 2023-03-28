package com.todo.exceptions;

public interface ErrorCode {
    Integer DUPLICATE_COLUMN_EXCEPTION = 100;
    Integer UNHANDLED_EXCEPTION=1001;
    Integer NO_AUTH_HEADER_FOUND=1002;
    Integer INVALID_INPUT_GIVEN = 1003;
    Integer INVALID_SERVELET_REQUEST=1005;
    Integer INVALID_ID_GIVEN_FOR_RESOURCE=1006;
    Integer UNAUTHORIZED=1007;
}

package com.todo.exceptions;

public interface ErrorMessage {
    String SOMETHING_WENT_WRONG = "Something went wrong, Please try again after some time!!!";
    String FIELD_ERROR_MSG = "Invalid or missing fields in request payload";
    String DB_CONSTAINT_ERROR_MSG = "Resource Already Exist with the provided information";
    String RESOUCE_DOES_NOT_EXISTS = "Resource doesn't exists with the provided information";
    String MISSING_OR_INVALID_CREDS = "Either something missing in payload or header or invalid creds are provided";
}
package com.todo.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Setter
@Getter
public class SuccessResponse<T> {
    private String message;
    private T data;

    public SuccessResponse<T> created(T data, Class entity) {
        String action = " created ";
        return getSuccessResponse(data, entity, action);
    }

    public SuccessResponse<T> retrieved(T data, Class entity) {
        String action = " retrieved ";
        return getSuccessResponse(data, entity, action);
    }

    public SuccessResponse<T> updated(T data, Class entity) {
        String action = " updated ";
        return getSuccessResponse(data, entity, action);
    }

    public SuccessResponse<T> deleted(T data, Class entity) {
        String action = " deleted ";
        return getSuccessResponse(data, entity, action);
    }

    public SuccessResponse<T> ok() {
        SuccessResponse<T> success = new SuccessResponse<T>();
        success.setData(data);
        success.setMessage("Request Completed SuccessFully");
        return success;
    }

    public SuccessResponse<T> ok(String message) {
        SuccessResponse<T> success = new SuccessResponse<T>();
        success.setMessage(message);
        success.setData(data);
        return success;
    }

    public SuccessResponse<T> ok(T data, String message) {
        SuccessResponse<T> success = new SuccessResponse<T>();
        success.setMessage(message);
        success.setData(data);
        return success;
    }


    private SuccessResponse<T> getSuccessResponse(T data, Class entity, String action) {
        SuccessResponse<T> success = new SuccessResponse<T>();
        success.setData(data);
        success.setMessage(StringUtils.capitalize(entity.getSimpleName()) + action + "Successfully");
        return success;
    }
}

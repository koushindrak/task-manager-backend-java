package com.todo.dao;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ResponseDTO {
    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseDTO ok(int code, Object data, String message) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(code);
        responseDTO.setData(data);
        responseDTO.setMessage(message);
        return responseDTO;
    }

    public ResponseDTO ok(String message) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(200);
        responseDTO.setData(new HashMap<>());
        responseDTO.setMessage(message);
        return responseDTO;
    }

    public ResponseDTO exception(int code, String message) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(code);
        responseDTO.setMessage(message);
        responseDTO.setData(new HashMap<>());
        return responseDTO;
    }
}

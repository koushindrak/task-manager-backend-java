package com.todo.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.exceptions.ErrorCategory;
import com.todo.exceptions.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * AuthenticationEntryPoint is used to handle failure/exception is while authenticating creds
 */
@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        log.info("\n\n INSIDE COMMENCE METHOD ==>");
        Iterator iterator = request.getAttributeNames().asIterator();
        while (iterator.hasNext()) {
            var attributeName = iterator.next().toString();
            if(!attributeName.equals("org.springframework.core.convert.ConversionService")){
                log.info(attributeName + "==" + request.getAttribute(attributeName).toString());
            }
        }
        Throwable throwable = (Throwable) request.getAttribute("jakarta.servlet.error.exception");
        Integer statusCode = 0;
        String message = authException.getMessage();
        if (throwable != null) {
            message = throwable.getMessage();
        } else {
            var attr = request.getAttribute("jakarta.servlet.error.status_code");
            if (attr != null) {
                statusCode = Integer.valueOf(String.valueOf(attr));
                message = "Invalid Api Path - " + request.getAttribute("jakarta.servlet.error.request_uri");
            }
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        map.put("errorCategory", ErrorCategory.CREDENTIALS_ERROR);
        map.put("errorCode", ErrorCode.UNAUTHORIZED_ACCESS);
        map.put("displayError", message);
        map.put("detail", authException.getMessage());
        map.put("httpStatus", HttpStatus.UNAUTHORIZED.value());

        if (HttpStatus.NOT_FOUND.value() == statusCode) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            map.put("errorCategory", ErrorCategory.CLIENT_SIDE_ERROR);
            map.put("errorCode", ErrorCode.INVALID_API_PATH);
            map.put("displayError", message);
            map.put("detail", "There is some spelling mistake in API path, Please check and fix it");
            map.put("httpStatus", HttpStatus.NOT_FOUND.value());
        }
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), map);
    }
}
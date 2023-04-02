package com.todo.security.filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
        log.info("\n\n INSIDE MyAccessDeniedHandler class ==>", accessDeniedException);

        writeCustomResponse(response);
    }

    private void writeCustomResponse(HttpServletResponse response) {
        if (!response.isCommitted()) {
            try {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");

                // response.getWriter().write("{ \"error\": \"User is not authorized.\"}");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
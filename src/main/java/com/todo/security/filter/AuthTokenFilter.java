package com.todo.security.filter;

import com.todo.context.ExecutionContext;
import com.todo.context.UserContext;
import com.todo.dao.UserRepository;
import com.todo.entity.User;
import com.todo.exceptions.AuthenticationException;
import com.todo.security.service.UserDetailsServiceImpl;
import com.todo.security.token.TokenRepository;
import com.todo.security.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);


                var isTokenValid = tokenRepository.findByToken(jwt)
                        .map(t -> !t.isExpired() && !t.isRevoked())
                        .orElse(false);

                if (jwtUtils.isTokenValid(jwt, userDetails) && isTokenValid) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    setExecutionContext(userDetails.getUsername());
                    String api_call = "\n\n Time - "+ Calendar.getInstance(TimeZone.getTimeZone("America/Toronto")).getTime() +" \n User "+userDetails.getUsername()+" called below api:\n\n"+httpServletRequestToString(request);
                    logger.info(api_call);
                    whenWriteStringUsingBufferedWritter_thenCorrect(api_call);
                }


            }
        } catch (Exception e) {
            boolean whileListed = false;
            for (String s : WebSecurityConfig.whiteListedApis) {
                if (request.getRequestURI().contains(s) || s.contains(request.getRequestURI())) {
                    whileListed = true;
                    break;
                }
            }
            if (!whileListed) {
                throw new RuntimeException(e.getMessage());
            } else {
                logger.error("\n\n Cannot set user authentication===>>>>: {}", e);
            }
        }

        filterChain.doFilter(request, response);
    }

    //DISABLE-COOKIE-FEATURE
//    private String parseJwt(HttpServletRequest request) {
//        String jwt = jwtUtils.getJwtFromCookies(request);
//        return jwt;
//    }
    public void whenWriteStringUsingBufferedWritter_thenCorrect(String str)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("api-calls.log",true));
        writer.write(str);
        writer.close();
    }
    private String parseJwt(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        logger.info("HEADER IS==== " + authHeader);
        boolean whileListed = false;
        for (String s : WebSecurityConfig.whiteListedApis) {
            if (request.getRequestURI().contains(s) || s.contains(request.getRequestURI())) {
                whileListed = true;
                break;
            }
        }
        if (whileListed) {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return authHeader;
            }
        } else {
            logger.info("\n\n\nRequest Details---\n" + httpServletRequestToString(request));
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new AuthenticationException("Invalid Header");
            }
        }

        return authHeader.substring(7);
    }

    private void setExecutionContext(String username) {
        User user = userRepository.findByUsername(username).get();
        UserContext userContext = new UserContext(user.getId(), user.getFirstName() + " " + user.getLastName(), user.getEmail(), user.getRole());
        ExecutionContext executionContext = new ExecutionContext(userContext);
        ExecutionContext.set(executionContext);
    }

    private String httpServletRequestToString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        sb.append(" Request Method = [" + request.getMethod() + "], \n");
        sb.append(" Request URL Path = [" + request.getRequestURL() + "], \n");

        String headers =
                Collections.list(request.getHeaderNames()).stream()
                        .map(headerName -> headerName + " : " + Collections.list(request.getHeaders(headerName)))
                        .collect(Collectors.joining(", "));

        if (headers.isEmpty()) {
            sb.append(" Request headers: NONE,\n");
        } else {
            sb.append(" Request headers: [" + headers + "],\n");
        }

        String parameters =
                Collections.list(request.getParameterNames()).stream()
                        .map(p -> p + " : " + Arrays.asList(request.getParameterValues(p)))
                        .collect(Collectors.joining(", "));

        if (parameters.isEmpty()) {
            sb.append(" Request parameters: NONE.\n");
        } else {
            sb.append(" Request parameters: [" + parameters + "].\n");
        }

        return sb.toString();
    }
}
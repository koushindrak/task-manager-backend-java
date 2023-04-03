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

import java.io.IOException;

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
                }


            }
        } catch (Exception e) {
            boolean whileListed = false;
            for (String s : WebSecurityConfig.whiteListedApis){
                if(request.getRequestURI().contains(s) || s.contains(request.getRequestURI())){
                    whileListed = true;
                    break;
                }
            }
            if(!whileListed){
                throw new RuntimeException(e.getMessage());
            }else {
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
    private String parseJwt(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        logger.info("HEADER IS==== " + authHeader);
        boolean whileListed = false;
        for (String s : WebSecurityConfig.whiteListedApis){
            if(request.getRequestURI().contains(s) || s.contains(request.getRequestURI())){
                whileListed = true;
                break;
            }
        }
        if(whileListed){
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return authHeader;
            }
        }else {
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
}
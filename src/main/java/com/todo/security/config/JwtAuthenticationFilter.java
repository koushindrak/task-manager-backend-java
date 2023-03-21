package com.todo.security.config;

import com.todo.context.ExecutionContext;
import com.todo.context.UserContext;
import com.todo.dao.UserRepository;
import com.todo.entity.User;
import com.todo.security.token.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final TokenRepository tokenRepository;
  private final UserRepository repository;

  Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userEmail;
    logger.info("HEADER IS==== "+authHeader);
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    jwt = authHeader.substring(7);
    userEmail = jwtService.extractUsername(jwt);
    logger.info("EMAIL IS==="+userEmail);
    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
      var isTokenValid = tokenRepository.findByToken(jwt)
          .map(t -> !t.isExpired() && !t.isRevoked())
          .orElse(false);
      if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
        setExecutionContext(userEmail);
      }
    }
    try {
      filterChain.doFilter(request, response);
    }catch (Exception e){
      logger.info("============== EXCEPTION IN FILTERS-----"+e);
    }
  }

  private void setExecutionContext(String userEmail) {
    User user = repository.findByEmail(userEmail).get();
    UserContext userContext = new UserContext(user.getId(),user.getFirstName()+" "+user.getLastName(), userEmail,user.getRole());
    ExecutionContext executionContext = new ExecutionContext(userContext);
    ExecutionContext.set(executionContext);
  }
}

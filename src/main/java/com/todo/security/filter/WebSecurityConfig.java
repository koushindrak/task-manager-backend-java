package com.todo.security.filter;

import com.todo.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
//@EnableGlobalMethodSecurity(
//        // securedEnabled = true,
//        // jsr250Enabled = true,
//        pre[PostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

//    private final AuthenticationProvider authenticationProvider;

    static List<String> whiteListedApis = Arrays.asList("/api/v1/auth/", "/api/v2/auth/",
            "/swagger-ui/", "/v3/api-docs.yml", "/api-docs/", "/users/", "/ping/");
    private final AuthTokenFilter authTokenFilter;
    private final LogoutHandler logoutHandler;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt authEntryPointJwt;
    private final MyAccessDeniedHandler myAccessDeniedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
//
//    return http.build();
//  }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors().and().csrf().disable()

                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler).and()

                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v2/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs.yml").permitAll()
                .requestMatchers("/api-docs/**").permitAll()
                .requestMatchers("/users/**").permitAll()
                .requestMatchers("/ping/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authenticationProvider(authenticationProvider())

                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/signout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("OPTIONS,POST, GET,PUT, OPTIONS, DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

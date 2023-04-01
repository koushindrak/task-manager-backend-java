package com.todo.security.auth;

import com.todo.dto.response.SuccessResponse;
import com.todo.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@Tag(name = "0-Authentication Controller", description = "Used for signup and sign")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/signup")
    public SuccessResponse register(@Valid @RequestBody RegisterRequest request, HttpServletRequest httpServletRequest) {
        service.register(request, httpServletRequest);
        return new SuccessResponse().created("User Registered Successfully!!!", User.class);
    }

    @PostMapping("/signin")
    public SuccessResponse<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return new SuccessResponse<AuthenticationResponse>().retrieved(service.authenticate(request), User.class);
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        service.verify(code);
        return "User is verified";
    }
}

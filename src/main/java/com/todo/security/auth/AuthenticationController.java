package com.todo.security.auth;

import com.todo.dto.response.SuccessResponse;
import com.todo.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "0-Authentication Controller",description = "Used for signup and sign")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/signup")
    public SuccessResponse register(@Valid @RequestBody RegisterRequest request) {
        service.register(request);
        return new SuccessResponse().created("User Registered Successfully!!!", User.class);
    }

    @PostMapping("/signin")
    public SuccessResponse<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return new SuccessResponse<AuthenticationResponse>().retrieved(service.authenticate(request), User.class);
    }


}

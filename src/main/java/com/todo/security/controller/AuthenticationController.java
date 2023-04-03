package com.todo.security.controller;

import com.todo.a_utils.UserUtils;
import com.todo.dto.response.SuccessResponse;
import com.todo.dto.response.UserResponse;
import com.todo.entity.User;
import com.todo.security.dto.AuthenticationRequest;
import com.todo.security.dto.AuthenticationResponse;
import com.todo.security.dto.RegisterRequest;
import com.todo.security.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Hidden;
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
    public SuccessResponse<UserResponse> register(@Valid @RequestBody RegisterRequest request, HttpServletRequest httpServletRequest) {
        User user = service.signup(request,httpServletRequest);
        return new SuccessResponse<UserResponse>().ok(UserUtils.toUserResponse(user),"An Email is sent with verification link, pls verify to complete your registration");
    }

    @PostMapping("/signin")
    public SuccessResponse<AuthenticationResponse> signin(@Valid @RequestBody AuthenticationRequest request) {
        return new SuccessResponse<AuthenticationResponse>().retrieved(service.signin(request), User.class);
    }

    @PostMapping("/signout")
    public SuccessResponse<AuthenticationResponse> signout() {
        //TODO: implemention pending
        return null;
    }

    @Hidden
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        service.verify(code);
        return "User is verified";
    }
}

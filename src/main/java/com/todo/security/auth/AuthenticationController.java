package com.todo.security.auth;

import com.todo.dto.ResponseDTO;
import com.todo.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/signup")
  public ResponseDTO.SuccessResponse register(@Valid @RequestBody RegisterRequest request) {
    service.register(request);
    return new ResponseDTO().created("User Registered Successfull!!!", User.class);
  }
  @PostMapping("/signin")
  public ResponseDTO.SuccessResponse authenticate(@Valid @RequestBody AuthenticationRequest request) {
    return new ResponseDTO().retrieved(service.authenticate(request), User.class);
  }


}

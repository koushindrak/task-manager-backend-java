//package com.todo.security.controller;
//
//import com.todo.a_utils.UserUtils;
//import com.todo.constants.ERole;
//import com.todo.dao.RoleRepository;
//import com.todo.dao.UserRepository;
//import com.todo.dto.response.SuccessResponse;
//import com.todo.dto.response.UserResponse;
//import com.todo.entity.Role;
//import com.todo.entity.User;
//import com.todo.exceptions.AuthenticationException;
//import com.todo.exceptions.ResourceAlreadyExistsException;
//import com.todo.security.dto.*;
//import com.todo.security.service.AuthenticationService;
//import com.todo.security.service.UserDetailsImpl;
//import com.todo.security.utils.JwtUtils;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseCookie;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api/v2/auth")
//public class AuthController {
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    @Autowired
//    JwtUtils jwtUtils;
//
//    @Autowired
//    AuthenticationService authenticationService;
//
//    @PostMapping("/signin")
//    public SuccessResponse<AuthenticationResponse> authenticateUser(@Valid @RequestBody AuthenticationRequest loginRequest, HttpServletRequest httpServletRequest) {
//        return new SuccessResponse<AuthenticationResponse>().retrieved(authenticationService.authenticate(loginRequest), User.class);
//    }
//
//    @PostMapping("/signup")
//    public SuccessResponse<UserResponse> registerUser(@Valid @RequestBody RegisterRequest signUpRequest, HttpServletRequest httpServletRequest) {
//        User user = authenticationService.register(signUpRequest,httpServletRequest);
//      return new SuccessResponse<UserResponse>().ok(UserUtils.toUserResponse(user),"User Registered Successfully");
//    }
//
////    @PostMapping("/signout")
////    public ResponseEntity<?> logoutUser() {
////        User user =
////        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
////        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
////                .body(new MessageResponse("You've been signed out!"));
////    }
//
//    private void setUserRoles(RegisterRequest signUpRequest, User user) {
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "mod":
//                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//
//        user.setRoles(roles);
//    }
//}
package com.todo.security.service;

import com.todo.constants.ERole;
import com.todo.dao.RoleRepository;
import com.todo.dao.UserRepository;
import com.todo.entity.LoginDetails;
import com.todo.entity.Role;
import com.todo.entity.User;
import com.todo.exceptions.AuthenticationException;
import com.todo.exceptions.ResourceAlreadyExistsException;
import com.todo.security.dto.AuthenticationRequest;
import com.todo.security.dto.AuthenticationResponse;
import com.todo.security.dto.RegisterRequest;
import com.todo.security.token.TokenRepository;
import com.todo.security.token.TokenType;
import com.todo.security.utils.JwtUtils;
import com.todo.ses.JavaMailService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtService;
    private final AuthenticationManager authenticationManager;
    private final JavaMailService javaMailService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User signup(RegisterRequest request, HttpServletRequest httpServletRequest) {

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            if (optionalUser.get().isEnabled()) {
                throw new ResourceAlreadyExistsException("Error: Email is already in use!");
            }
        }

        optionalUser = userRepository.findByUsername(request.getUsername());
        if (optionalUser.isPresent()) {
            if (optionalUser.get().isEnabled()) {
                throw new ResourceAlreadyExistsException("Error: Username is already taken!");
            }
        }

        String randomCode = RandomStringUtils.random(64, true, true);
        User user = optionalUser.orElseGet(User::new);
        toUser(request, randomCode, user);
        user = repository.save(user);
        try {
            javaMailService.sendVerificationEmail(user, getSiteURL(httpServletRequest));
            log.info("********** REGISTRATION MAIL SENT TO USER "+user.getEmail()+ " SuccessFully!!!");
        } catch (MessagingException e) {
            log.info("===== EXCEPTION WHILE SENDING VERIFICATION EMAIL-1 ===== ", e);
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            log.info("===== EXCEPTION WHILE SENDING VERIFICATION EMAIL-2 ===== ", e);
            throw new RuntimeException(e);
        }
        return user;
    }

    private void toUser(RegisterRequest request, String randomCode, User user) {
        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        setUserRoles(request, user);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    public void verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (Objects.isNull(user)) {
            throw new AuthenticationException("Invalid verification code");
        }
        if (user.isEnabled()) {
            throw new AuthenticationException("Verification code is already used");
        }

        if (ChronoUnit.MINUTES.between(user.getUpdatedAt().toInstant(), Instant.now()) > 15) {
            user.setVerificationCodeExpired(true);
            user.setEnabled(false);
            userRepository.save(user);
            throw new AuthenticationException("Verification Code is expired");
        }

        user.setVerificationCodeExpired(true);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse signin(AuthenticationRequest request) {
        log.info("Authenticating user");
       User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new AuthenticationException("Username doesn't exists"));
        if(!user.isEnabled()){
            throw new AuthenticationException("User is not Verified!!!");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());


        var jwtToken = jwtService.generateToken(user);
        log.info("revoking old token for user");
        revokeAllUserTokens(user);
        log.info("Saving user token");
        saveUserToken(user, jwtToken);
        log.info("... Building jwt token");
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = LoginDetails.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }


    private void setUserRoles(RegisterRequest signUpRequest, User user) {
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
    }

}

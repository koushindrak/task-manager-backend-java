package com.todo.security.auth;

import com.todo.constants.Role;
import com.todo.dao.UserRepository;
import com.todo.entity.LoginDetails;
import com.todo.entity.User;
import com.todo.exceptions.AuthenticationException;
import com.todo.exceptions.ResourceNotFoundException;
import com.todo.security.config.JwtService;
import com.todo.security.token.TokenRepository;
import com.todo.security.token.TokenType;
import com.todo.ses.JavaMailService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JavaMailService javaMailService;
    private final UserRepository userRepository;

    public void register(RegisterRequest request, HttpServletRequest httpServletRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            if (!optionalUser.get().isEnabled()) {
                userRepository.delete(optionalUser.get());
            }
        }

        String randomCode = RandomStringUtils.random(64, true, true);

        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .verificationCode(randomCode)
                .enabled(false)
                .build();

        repository.save(user);
        try {
            javaMailService.sendVerificationEmail(user, getSiteURL(httpServletRequest));
        } catch (MessagingException e) {
            log.info("===== EXCEPTION WHILE SENDING VERIFICATION EMAIL-1 ===== ", e);
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            log.info("===== EXCEPTION WHILE SENDING VERIFICATION EMAIL-2 ===== ", e);
            throw new RuntimeException(e);
        }

    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    public void verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (Objects.isNull(user)) {
            throw new AuthenticationException(401, "Invalid verification code");
        }
        if (user.isEnabled()) {
            throw new AuthenticationException(401, "Verification code is already used");
        }
        if (ChronoUnit.MINUTES.between(user.getCreatedAt().toInstant(), Instant.now()) > 2) {
            user.setVerificationCodeExpired(true);
            user.setEnabled(false);
            userRepository.save(user);
            throw new AuthenticationException(401, "Verification Code is expired");
        }

        user.setVerificationCodeExpired(true);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("Authenticating user");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid User email"));
        log.info("User found with email-" + request.getEmail());
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


}

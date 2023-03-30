package com.todo.security.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "First name is mandatory")
    private String firstname;
    @NotBlank(message = "First name is mandatory")
    private String lastname;
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    @NotBlank
    @Size
    private String password;

}

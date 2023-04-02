package com.todo.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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
    @Email(message = "Invalid Email Format")
    private String email;

    @NotBlank(message = "username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 40,message = "Password length must be b/w 6 and 40")
    private String password;

    private Set<String> role;

}

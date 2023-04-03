package com.todo.context;

import com.todo.constants.ERole;

public record UserContext(Long id, String name, String email, ERole role) {
}

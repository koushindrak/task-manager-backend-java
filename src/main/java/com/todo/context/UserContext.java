package com.todo.context;

import com.todo.constants.Role;

public record UserContext(Long id, String name, String email, Role role) {
}

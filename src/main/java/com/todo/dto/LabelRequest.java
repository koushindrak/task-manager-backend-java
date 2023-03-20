package com.todo.dto;

import com.todo.entity.Task;
import com.todo.entity.User;
import jakarta.persistence.*;

import java.util.Set;

public record LabelRequest(String name, String description) {
}

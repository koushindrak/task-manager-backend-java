package com.todo.dto;

import com.todo.entity.Task;
import com.todo.entity.User;
import jakarta.persistence.*;

import java.util.Set;

public class LabelResponse {

    private Long id;

    private String name;

    private String description;

}

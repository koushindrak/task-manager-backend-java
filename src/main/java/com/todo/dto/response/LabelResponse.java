package com.todo.dto.response;

import com.todo.entity.Task;
import com.todo.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
public class LabelResponse {

    private Long id;

    private String name;

    private String description;

}

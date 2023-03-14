package com.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriorityResponseDto {

    private Long id;

    private String name;

    private String description;

    private int value;

    private String status;
}

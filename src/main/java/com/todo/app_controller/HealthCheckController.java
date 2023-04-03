package com.todo.app_controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping(("/ping"))
public class HealthCheckController {

    @GetMapping
    public String getHealthCheckStatus() {
        return "OK";
    }
}

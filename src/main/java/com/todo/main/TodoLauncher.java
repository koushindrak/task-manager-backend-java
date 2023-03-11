package com.todo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.todo.*")
public class TodoLauncher {

    public static void main(String[] args) {
        SpringApplication.run(TodoLauncher.class, args);
    }

}

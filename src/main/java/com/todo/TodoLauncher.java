package com.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.todo.**")
@EnableJpaRepositories(basePackages = {"com.todo.dao","com.todo.security.token"})
public class TodoLauncher {

    public static void main(String[] args) {
        SpringApplication.run(TodoLauncher.class, args);
    }

}
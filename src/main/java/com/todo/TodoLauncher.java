package com.todo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = "com.todo.**")
@EnableJpaRepositories(basePackages = {"com.todo.dao", "com.todo.security.token"})
@EnableScheduling
public class TodoLauncher  {

    public static void main(String[] args) {
        SpringApplication.run(TodoLauncher.class, args);
    }

}

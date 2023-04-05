//package com.todo.jdbc;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.stream.Collectors;
//
//@Component
//public class SchemaLoader {
//
//  @Autowired
//  private ResourceLoader resourceLoader;
//
//  public String loadSchema() throws IOException {
//    Resource resource = resourceLoader.getResource("classpath:todo-schema.sql");
//    InputStream inputStream = resource.getInputStream();
//    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//      return reader.lines().collect(Collectors.joining("\n"));
//    }
//  }
//}

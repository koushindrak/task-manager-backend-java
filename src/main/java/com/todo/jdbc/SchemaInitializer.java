//package com.todo.jdbc;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class SchemaInitializer {
//
//  @Autowired
//  private JdbcTemplate jdbcTemplate;
//
//  @Autowired
//  private SchemaLoader schemaLoader;
//
//  @PostConstruct
//  public void initialize() throws IOException {
//    String schemaSql = schemaLoader.loadSchema();
//    jdbcTemplate.execute(schemaSql);
//  }
//}

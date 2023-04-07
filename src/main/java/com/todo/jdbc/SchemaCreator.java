package com.todo.jdbc;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Slf4j
public class SchemaCreator {
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void ru1() throws Exception {
        try {
            Resource schema = new ClassPathResource("schema.sql");
            ScriptUtils.executeSqlScript(dataSource.getConnection(), schema);
        }catch (Exception e){
            log.error("\n\n-----Exception occured while Creating Schema---"+e.getMessage()+"\n\n");
        }
    }
}

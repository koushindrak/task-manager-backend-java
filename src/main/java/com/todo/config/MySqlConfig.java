package com.todo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MySqlConfig {
    @Value("${mysql.driverClassName}")
    private String driverClassName;

    @Value("${mysql.url}")
    private String url;

    @Value("${mysql.username}")
    private String username;

    @Value("${mysql.password}")
    private String password;

    @Value("${mysql.dialect}")
    private String dialect;

    @Value("${mysql.show_sql}")
    private Boolean show_sql;

    @Value("${mysql.hibernateCreateMode}")
    private String hibernateCreateMode;

    public String getHibernateCreateMode() {
        return hibernateCreateMode;
    }

    public void setHibernateCreateMode(String hibernateCreateMode) {
        this.hibernateCreateMode = hibernateCreateMode;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public Boolean getShow_sql() {
        return show_sql;
    }

    public void setShow_sql(Boolean show_sql) {
        this.show_sql = show_sql;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(System.lineSeparator() + "MySqlConfig{" + System.lineSeparator());
        sb.append("\tdriverClassName=").append(driverClassName).append(System.lineSeparator());
        sb.append("\turl=").append(url).append(System.lineSeparator());
        sb.append("\tusername=").append(username).append(System.lineSeparator());
        sb.append("\tpassword=").append(password).append(System.lineSeparator());
        sb.append("\tdialect=").append(dialect).append(System.lineSeparator());
        sb.append("\tshow_sql=").append(show_sql).append(System.lineSeparator());
        sb.append("\thibernateCreateMode=").append(hibernateCreateMode).append(System.lineSeparator());
        sb.append('}' + System.lineSeparator());
        return sb.toString();
    }
}


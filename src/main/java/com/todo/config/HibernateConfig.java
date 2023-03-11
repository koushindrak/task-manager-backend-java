//package com.todo.config;
//
//import com.todo.constants.ApplicationConstants;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@Import(MySqlConfig.class)
//public class HibernateConfig {
//
//    private final MySqlConfig mySqlConfig;
//
//    private static final Logger LOG = LogManager.getLogger();
//
//    public HibernateConfig(MySqlConfig mySqlConfig) {
//        this.mySqlConfig = mySqlConfig;
//    }
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan(ApplicationConstants.HIBERNATE_PACKAGE_SCAN);
//        sessionFactory.setHibernateProperties(hibernateProperties());
//
//        return sessionFactory;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        System.out.println(mySqlConfig.toString());
//
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(mySqlConfig.getDriverClassName());
//        dataSource.setUrl(mySqlConfig.getUrl());
//        dataSource.setUsername(mySqlConfig.getUsername());
//        dataSource.setPassword(mySqlConfig.getPassword());
//
//        return dataSource;
//    }
//
//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
//        LOG.info("setting hibernate properties------------------------------------");
//        System.out.println(mySqlConfig);
//        properties.put(ApplicationConstants.HIBERNATE_DIALECT, mySqlConfig.getDialect());
//        properties.put(ApplicationConstants.HIBERNATE_SHOW_SQL, mySqlConfig.getShow_sql());
//        properties.put(ApplicationConstants.HIBERNATE_DDL_MODE, mySqlConfig.getHibernateCreateMode());
//
//        return properties;
//    }
//}

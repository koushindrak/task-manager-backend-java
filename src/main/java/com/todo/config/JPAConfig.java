package com.todo.config;

import com.todo.constants.ApplicationConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import(MySqlConfig.class)
@EnableTransactionManagement
public class JPAConfig {
    private static final Logger LOG = LogManager.getLogger();
    private final MySqlConfig mySqlConfig;

    public JPAConfig(MySqlConfig mySqlConfig) {
        this.mySqlConfig = mySqlConfig;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(mySqlConfig.getDriverClassName());
        dataSource.setUrl(mySqlConfig.getUrl());
        dataSource.setUsername(mySqlConfig.getUsername());
        dataSource.setPassword(mySqlConfig.getPassword());
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(ApplicationConstants.HIBERNATE_PACKAGE_SCAN);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        LOG.info("setting hibernate properties------------------------------------");
        System.out.println(mySqlConfig);
        properties.put(ApplicationConstants.HIBERNATE_DIALECT, mySqlConfig.getDialect());
        properties.put(ApplicationConstants.HIBERNATE_SHOW_SQL, mySqlConfig.getShow_sql());
        properties.put(ApplicationConstants.HIBERNATE_DDL_MODE, mySqlConfig.getHibernateCreateMode());
        return properties;

    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}

package com.example.demojpamultidatasource.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.demojpamultidatasource.repository", entityManagerFactoryRef = "userMultiEntityManager", transactionManagerRef = "userMultiTransactionManager")
public class UserDbConfig {
    @Bean(name = "userBasicDataSource")
    @ConfigurationProperties("app.datasource.basic")
    public DataSource userBasicDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "userPremiumDataSource")
    @ConfigurationProperties("app.datasource.premium")
    public DataSource userPremiumDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "userMultiRoutingDataSource")
    public DataSource userMultiRoutingDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(UserMembershipType.BASIC, userBasicDataSource());
        targetDataSources.put(UserMembershipType.PREMIUM, userPremiumDataSource());

        UserMultiRoutingDataSource userMultiRoutingDataSource = new UserMultiRoutingDataSource();
        userMultiRoutingDataSource.setDefaultTargetDataSource(userBasicDataSource());
        userMultiRoutingDataSource.setTargetDataSources(targetDataSources);
        return userMultiRoutingDataSource;
    }

    @Bean(name = "userMultiEntityManager")
    public LocalContainerEntityManagerFactoryBean userMultiEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userMultiRoutingDataSource());
        em.setPackagesToScan("com.example.demojpamultidatasource.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    @Bean(name = "userMultiTransactionManager")
    public PlatformTransactionManager userMultiTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(userMultiEntityManager().getObject());

        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);

        return properties;
    }
}

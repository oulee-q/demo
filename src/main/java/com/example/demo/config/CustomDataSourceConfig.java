package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CustomDataSourceConfig {

    @Bean
    public DataSource dataSource(CustomProjectProperties properties) {
        CustomProjectProperties.Database db = properties.getDatabase();
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(db.getUrl());
        dataSource.setUsername(db.getUsername());
        dataSource.setPassword(db.getPassword());
        dataSource.setDriverClassName(db.getDriverClassName());
        dataSource.setPoolName("custom-project-pool");
        dataSource.setMaximumPoolSize(5);
        return dataSource;
    }
}

package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class CustomDataSourceConfigTest {

    @Test
    void shouldCreateHikariDataSourceFromCustomProjectDatabaseConfig() {
        CustomProjectProperties properties = new CustomProjectProperties();
        properties.setName("demo");
        properties.getDatabase().setUrl("jdbc:h2:mem:testdb");
        properties.getDatabase().setUsername("sa");
        properties.getDatabase().setPassword("pwd123");
        properties.getDatabase().setDriverClassName("org.h2.Driver");

        DataSource dataSource = new CustomDataSourceConfig().dataSource(properties);

        HikariDataSource hikari = assertInstanceOf(HikariDataSource.class, dataSource);
        assertEquals("jdbc:h2:mem:testdb", hikari.getJdbcUrl());
        assertEquals("sa", hikari.getUsername());
        assertEquals("pwd123", hikari.getPassword());
        assertEquals("org.h2.Driver", hikari.getDriverClassName());
        assertEquals("custom-project-pool", hikari.getPoolName());
        assertEquals(5, hikari.getMaximumPoolSize());
        hikari.close();
    }
}

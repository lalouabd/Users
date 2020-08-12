package com.bandg.users.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {
    @Bean
    @ConfigurationProperties("app.flyway")
    public HikariDataSource HikariDataSource()
    {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }
}

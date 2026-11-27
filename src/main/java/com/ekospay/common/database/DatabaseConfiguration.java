package com.ekospay.common.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {
    @Bean
    DataSource dataSource(@Value("${DATABASE_URL}") String databaseUrl) {
        DatabaseConnectionSettings settings = DatabaseConnectionSettings.fromPostgresUrl(databaseUrl);

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(settings.jdbcUrl());
        config.setUsername(settings.username());
        config.setPassword(settings.password());
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(0);
        config.setPoolName("ekospay-database-pool");
        return new HikariDataSource(config);
    }
}

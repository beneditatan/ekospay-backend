package com.ekospay.common.health;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class DatabaseHealthController {
    private final JdbcClient jdbcClient;

    public DatabaseHealthController(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @GetMapping("/database")
    DatabaseHealthResponse databaseHealth() {
        String databaseName = jdbcClient.sql("select current_database()")
                .query(String.class)
                .single();
        return new DatabaseHealthResponse("UP", databaseName);
    }

    record DatabaseHealthResponse(String status, String database) {
    }
}

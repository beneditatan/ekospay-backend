package com.ekospay.common.database;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public record DatabaseConnectionSettings(String jdbcUrl, String username, String password) {
    public static DatabaseConnectionSettings fromPostgresUrl(String databaseUrl) {
        URI uri = URI.create(databaseUrl);
        if (!"postgresql".equals(uri.getScheme()) && !"postgres".equals(uri.getScheme())) {
            throw new IllegalArgumentException("DATABASE_URL must use the postgresql scheme");
        }

        String[] credentials = requireUserInfo(uri).split(":", 2);
        if (credentials.length != 2) {
            throw new IllegalArgumentException("DATABASE_URL must include a username and password");
        }

        int port = uri.getPort() == -1 ? 5432 : uri.getPort();
        String jdbcUrl = "jdbc:postgresql://%s:%d%s?sslmode=require"
                .formatted(uri.getHost(), port, uri.getPath());

        return new DatabaseConnectionSettings(
                jdbcUrl,
                decode(credentials[0]),
                decode(credentials[1])
        );
    }

    private static String requireUserInfo(URI uri) {
        if (uri.getRawUserInfo() == null || uri.getRawUserInfo().isBlank()) {
            throw new IllegalArgumentException("DATABASE_URL must include database credentials");
        }
        return uri.getRawUserInfo();
    }

    private static String decode(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }
}

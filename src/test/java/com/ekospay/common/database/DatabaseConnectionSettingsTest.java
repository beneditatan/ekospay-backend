package com.ekospay.common.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class DatabaseConnectionSettingsTest {
    @Test
    void parsesNeonPostgresUrlForJdbc() {
        DatabaseConnectionSettings settings = DatabaseConnectionSettings.fromPostgresUrl(
                "postgresql://app%40user:p%40ss@example.neon.tech/appdb?sslmode=require"
        );

        assertThat(settings.jdbcUrl()).isEqualTo(
                "jdbc:postgresql://example.neon.tech:5432/appdb?sslmode=require"
        );
        assertThat(settings.username()).isEqualTo("app@user");
        assertThat(settings.password()).isEqualTo("p@ss");
    }

    @Test
    void rejectsUrlsWithoutCredentials() {
        assertThatThrownBy(() -> DatabaseConnectionSettings.fromPostgresUrl(
                "postgresql://example.neon.tech/appdb"
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("credentials");
    }
}

package io.github.mfvanek.postgres.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.annotation.Nonnull;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HikariDataSourceProvider {

    @Nonnull
    @SneakyThrows
    HikariDataSource getDataSource(@Nonnull final String jdbcUrl,
                                   @Nonnull final String username,
                                   @Nonnull final String password) {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(Class.forName("org.postgresql.Driver").getCanonicalName());
        return new HikariDataSource(hikariConfig);
    }
}

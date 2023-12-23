package io.github.mfvanek.postgres.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.Driver;

import javax.annotation.Nonnull;

public final class HikariDataSourceProvider {

    @Nonnull
    public static HikariDataSource getDataSource(@Nonnull final String jdbcUrl,
                                                 @Nonnull final String username,
                                                 @Nonnull final String password) {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setMaximumPoolSize(3);
        hikariConfig.setConnectionTimeout(250L);
        hikariConfig.setMaxLifetime(30_000L);
        hikariConfig.setValidationTimeout(250L);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(Driver.class.getCanonicalName());
        return new HikariDataSource(hikariConfig);
    }
}

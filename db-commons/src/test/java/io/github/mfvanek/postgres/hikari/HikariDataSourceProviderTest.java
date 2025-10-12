package io.github.mfvanek.postgres.hikari;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import javax.annotation.Nonnull;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class HikariDataSourceProviderTest {

    @Test
    void getDataSourceShouldSetDriverClassName() {
        try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
                DockerImageName.parse("postgres").withTag("17.6"))
                .withTmpFs(Collections.singletonMap("/var/lib/postgresql/data", "rw"))
                .withDatabaseName("test_db")
                .withUsername("test_user")
                .withPassword("test_password")
                .waitingFor(Wait.defaultWaitStrategy())) {
            postgres.start();

            try (HikariDataSource dataSource = getDataSource(postgres)) {
                assertThat(dataSource.getDriverClassName())
                        .isEqualTo("org.postgresql.Driver");
            }
        }
    }

    private static HikariDataSource getDataSource(@Nonnull final JdbcDatabaseContainer<?> container) {
        return HikariDataSourceProvider.getDataSource(
                container.getJdbcUrl(), container.getUsername(), container.getPassword());
    }
}

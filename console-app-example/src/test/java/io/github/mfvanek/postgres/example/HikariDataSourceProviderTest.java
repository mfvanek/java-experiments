package io.github.mfvanek.postgres.example;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

import static io.github.mfvanek.postgres.example.SimpleTest.getDataSource;
import static org.assertj.core.api.Assertions.assertThat;

class HikariDataSourceProviderTest {

    @Test
    void getDataSourceShouldSetDriverClassName() {
        try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
                DockerImageName.parse("postgres").withTag("14.5"))
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
}

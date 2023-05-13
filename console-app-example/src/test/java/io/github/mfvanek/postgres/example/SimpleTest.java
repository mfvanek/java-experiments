package io.github.mfvanek.postgres.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;

import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

class SimpleTest {

    @Test
    void testSimple() throws SQLException {
        try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
                DockerImageName.parse("postgres").withTag("14.5"))
                .withTmpFs(Collections.singletonMap("/var/lib/postgresql/data", "rw"))
                .withDatabaseName("test_db")
                .withUsername("test_user")
                .withPassword("test_password")
                .waitingFor(Wait.defaultWaitStrategy())) {
            postgres.start();

            try (HikariDataSource dataSource = getDataSource(postgres);
                    ResultSet resultSet = performQuery(dataSource, "SELECT 1")) {
                int resultSetInt = resultSet.getInt(1);
                Assertions.assertEquals(1, resultSetInt);
            }
        }
    }

    private ResultSet performQuery(DataSource ds, String sql)
            throws SQLException {
        Statement statement = ds.getConnection().createStatement();
        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        return resultSet;
    }

    private HikariDataSource getDataSource(JdbcDatabaseContainer<?> container) {
        return HikariDataSourceProvider.getDataSource(
                container.getJdbcUrl(), container.getUsername(), container.getPassword());
    }
}

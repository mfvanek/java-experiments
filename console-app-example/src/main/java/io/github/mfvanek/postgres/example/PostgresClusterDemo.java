package io.github.mfvanek.postgres.example;

import com.zaxxer.hikari.HikariDataSource;
import io.github.mfvanek.pg.testing.PostgreSqlClusterWrapper;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import javax.sql.DataSource;

@Slf4j
public class PostgresClusterDemo {

    public static void main(String[] args) throws InterruptedException {
        try (var clusterWrapper = PostgreSqlClusterWrapper.builder()
                .withUsername("test_user")
                .withPassword("test_password")
                .withDatabaseName("test_db")
                .withPostgresVersion("15.2")
                .build()) {
            // TODO add ability to get connection string with all hosts
            log.info(clusterWrapper.getFirstContainerJdbcUrl());
            log.info(clusterWrapper.getSecondContainerJdbcUrl());

            final String pgUrl = PgUrlParser.buildCommonUrlToPrimary(
                    Set.of(clusterWrapper.getFirstContainerJdbcUrl(), clusterWrapper.getSecondContainerJdbcUrl()));
            log.info("pgUrl = {}", pgUrl);

            try (HikariDataSource dataSource = HikariDataSourceProvider.getDataSource(
                    pgUrl, clusterWrapper.getUsername(), clusterWrapper.getPassword())) {
                final AtomicLong counter = new AtomicLong(0L);
                final Thread action = new Thread(prepareAction(dataSource, counter));
                action.start();

                TimeUnit.SECONDS.sleep(5L);

                clusterWrapper.stopFirstContainer();

                TimeUnit.SECONDS.sleep(10L);

                action.interrupt();
            }
        }
    }

    private static Runnable prepareAction(final DataSource dataSource, final AtomicLong counter) {
        return () -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                try (Connection connection = dataSource.getConnection();
                        Statement statement = connection.createStatement()) {
                    statement.execute("select version()");
                    try (ResultSet resultSet = statement.getResultSet()) {
                        resultSet.next();
                        String pgVersion = resultSet.getString(1);
                        log.info("{} {}", counter.incrementAndGet(), pgVersion);
                    }
                    TimeUnit.MILLISECONDS.sleep(500L);
                } catch (SQLException e) {
                    log.warn(e.getMessage());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
    }
}

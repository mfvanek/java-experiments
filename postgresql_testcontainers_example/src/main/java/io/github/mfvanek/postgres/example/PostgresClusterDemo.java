package io.github.mfvanek.postgres.example;

import io.github.mfvanek.pg.testing.PostgreSqlClusterWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostgresClusterDemo {

    public static void main(String[] args) {
        try (var clusterWrapper = PostgreSqlClusterWrapper.builder()
            .withUsername("test_user")
            .withPassword("test_password")
            .build()) {
            // TODO add ability to setup database name
            // TODO add ability to force PG version
            // TODO add ability to get connection string with all hosts
            log.info(clusterWrapper.getFirstContainerJdbcUrl());
            log.info(clusterWrapper.getSecondContainerJdbcUrl());
        }
    }
}

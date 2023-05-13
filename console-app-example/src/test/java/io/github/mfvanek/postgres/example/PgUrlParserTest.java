package io.github.mfvanek.postgres.example;

import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PgUrlParserTest {

    @Test
    void extractDatabaseName() {
        assertThat(PgUrlParser.extractDatabaseName(Set.of("jdbc:postgresql://host:5432/db?param=1")))
                .isEqualTo("/db");

        assertThat(PgUrlParser.extractDatabaseName(Set.of("jdbc:postgresql://host:5432/db?")))
                .isEqualTo("/db");

        assertThat(PgUrlParser.extractDatabaseName(Set.of("jdbc:postgresql://host:5432/db")))
                .isEqualTo("/db");
    }

    @Test
    void buildCommonUrlToPrimary() {
        assertThat(PgUrlParser.buildCommonUrlToPrimary(Set.of("jdbc:postgresql://host:5432/db?param=1")))
                .isEqualTo("jdbc:postgresql://host:5432/db?targetServerType=primary");

        assertThat(PgUrlParser.buildCommonUrlToPrimary(Set.of(
                "jdbc:postgresql://host:5432/db?param=1",
                "jdbc:postgresql://anotherHost:5433/db?param2=test")))
                .isEqualTo("jdbc:postgresql://anotherHost:5433,host:5432/db?targetServerType=primary");
    }
}

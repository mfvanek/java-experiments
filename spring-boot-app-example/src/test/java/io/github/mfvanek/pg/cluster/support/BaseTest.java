package io.github.mfvanek.pg.cluster.support;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.threeten.extra.MutableClock;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

@SpringBootTest(
    classes = BaseTest.CustomClockConfiguration.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "pg.index.health.test.enabled=false")
@ActiveProfiles("test")
@AutoConfigureWebTestClient
public abstract class BaseTest {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @LocalServerPort
    protected int port;

    @LocalManagementPort
    protected int actuatorPort;

    @Autowired
    protected Clock clock;

    @Autowired
    protected WebTestClient webTestClient;

    @TestConfiguration
    static class CustomClockConfiguration {

        @Bean
        public MutableClock mutableClock() {
            final LocalDateTime beforeMillennium = LocalDateTime.of(1999, Month.DECEMBER, 31, 23, 59, 59);
            return MutableClock.of(beforeMillennium.toInstant(ZoneOffset.UTC), ZoneOffset.UTC);
        }

        @Bean
        @Primary
        public Clock fixedClock(@Nonnull final MutableClock mutableClock) {
            return mutableClock;
        }
    }
}

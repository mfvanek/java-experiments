package io.github.mfvanek.pg.cluster.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.mfvanek.pg.testing.PostgreSqlClusterWrapper;

import io.github.mfvanek.postgres.example.PgUrlParser;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.Map;
import javax.annotation.Nonnull;
import javax.sql.DataSource;

@Slf4j
@Configuration(proxyBeanMethods = false)
public class DatabaseConfig {

    @Bean(destroyMethod = "close")
    public PostgreSqlClusterWrapper clusterWrapper() {
        return PostgreSqlClusterWrapper.builder()
                .withUsername("test_user")
                .withPassword("test_password")
                .build();
    }

    @Bean
    public DataSource dataSource(@Nonnull final PostgreSqlClusterWrapper clusterWrapper,
                                 @Nonnull final Environment environment) {
        final String pgUrl = PgUrlParser.buildCommonUrlToPrimary(
                Set.of(clusterWrapper.getFirstContainerJdbcUrl(), clusterWrapper.getSecondContainerJdbcUrl()));
        log.info("pgUrl = {}", pgUrl);

        if (environment instanceof ConfigurableEnvironment configurableEnvironment) {
            final MutablePropertySources mps = configurableEnvironment.getPropertySources();
            mps.addFirst(new MapPropertySource("connectionString",
                    Map.ofEntries(Map.entry("spring.datasource.url", pgUrl))));
        }
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(pgUrl);
        hikariConfig.setUsername(clusterWrapper.getUsername());
        hikariConfig.setPassword(clusterWrapper.getPassword());
        return new HikariDataSource(hikariConfig);
    }
}

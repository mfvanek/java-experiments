package io.github.mfvanek.pg.cluster;

import io.github.mfvanek.pg.common.maintenance.DatabaseCheckOnHost;
import io.github.mfvanek.pg.common.maintenance.Diagnostic;
import io.github.mfvanek.pg.model.DbObject;
import io.github.mfvanek.pg.model.table.Table;
import io.github.mfvanek.pg.testing.PostgreSqlClusterWrapper;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.sql.DataSource;

import static io.github.mfvanek.postgres.hikari.HikariDataSourceProvider.getDataSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;

@SpringBootTest(
        classes = IndexesMaintenanceTest.CustomDatasourceConfiguration.class,
        webEnvironment = WebEnvironment.NONE,
        properties = "spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles({"test", "test-single-node"})
class IndexesMaintenanceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private List<DatabaseCheckOnHost<? extends DbObject>> checks;

    @Test
    @DisplayName("Always check PostgreSQL version in your tests")
    void checkPostgresVersion() {
        final String pgVersion = jdbcTemplate.queryForObject("select version();", String.class);
        assertThat(pgVersion)
                .startsWith("PostgreSQL 16.4");
    }

    @Test
    void databaseStructureCheckForPublicSchema() {
        assertThat(checks)
                .hasSameSizeAs(Diagnostic.values());

        checks.stream()
                .filter(DatabaseCheckOnHost::isStatic)
                .forEach(check -> {
                    final ListAssert<? extends DbObject> checkAssert = assertThat(check.check())
                            .as(check.getDiagnostic().name());

                    if (check.getDiagnostic() == Diagnostic.COLUMNS_WITHOUT_DESCRIPTION) {
                        checkAssert.hasSize(4);
                    } else if (check.getDiagnostic() == Diagnostic.TABLES_NOT_LINKED_TO_OTHERS) {
                        checkAssert.hasSize(1)
                                .asInstanceOf(list(Table.class))
                                .containsExactly(Table.of("employees", 0L));
                    } else {
                        checkAssert.isEmpty();
                    }
                });
    }

    @TestConfiguration
    public static class CustomDatasourceConfiguration {

        @Bean
        public DataSource dataSource(@Nonnull final PostgreSqlClusterWrapper clusterWrapper,
                                     @Nonnull final Environment environment) {
            final String pgUrl = clusterWrapper.getFirstContainerJdbcUrl();
            if (environment instanceof ConfigurableEnvironment configurableEnvironment) {
                final MutablePropertySources mps = configurableEnvironment.getPropertySources();
                mps.addFirst(new MapPropertySource("connectionString",
                        Map.ofEntries(Map.entry("spring.datasource.url", pgUrl))));
            }
            return getDataSource(pgUrl, clusterWrapper.getUsername(), clusterWrapper.getPassword());
        }
    }
}

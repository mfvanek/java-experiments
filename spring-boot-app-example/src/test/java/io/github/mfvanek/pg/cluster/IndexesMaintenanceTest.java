package io.github.mfvanek.pg.cluster;

import io.github.mfvanek.pg.checks.host.BtreeIndexesOnArrayColumnsCheckOnHost;
import io.github.mfvanek.pg.checks.host.ColumnsWithJsonTypeCheckOnHost;
import io.github.mfvanek.pg.checks.host.ColumnsWithSerialTypesCheckOnHost;
import io.github.mfvanek.pg.checks.host.ColumnsWithoutDescriptionCheckOnHost;
import io.github.mfvanek.pg.checks.host.DuplicatedIndexesCheckOnHost;
import io.github.mfvanek.pg.checks.host.ForeignKeysNotCoveredWithIndexCheckOnHost;
import io.github.mfvanek.pg.checks.host.FunctionsWithoutDescriptionCheckOnHost;
import io.github.mfvanek.pg.checks.host.IndexesWithBooleanCheckOnHost;
import io.github.mfvanek.pg.checks.host.IndexesWithNullValuesCheckOnHost;
import io.github.mfvanek.pg.checks.host.IntersectedIndexesCheckOnHost;
import io.github.mfvanek.pg.checks.host.InvalidIndexesCheckOnHost;
import io.github.mfvanek.pg.checks.host.NotValidConstraintsCheckOnHost;
import io.github.mfvanek.pg.checks.host.SequenceOverflowCheckOnHost;
import io.github.mfvanek.pg.checks.host.TablesWithoutDescriptionCheckOnHost;
import io.github.mfvanek.pg.checks.host.TablesWithoutPrimaryKeyCheckOnHost;
import io.github.mfvanek.pg.testing.PostgreSqlClusterWrapper;
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

import java.util.Map;
import javax.annotation.Nonnull;
import javax.sql.DataSource;

import static io.github.mfvanek.postgres.hikari.HikariDataSourceProvider.getDataSource;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = IndexesMaintenanceTest.CustomDatasourceConfiguration.class,
        webEnvironment = WebEnvironment.NONE,
        properties = "spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles({"test", "test-single-node"})
class IndexesMaintenanceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private InvalidIndexesCheckOnHost invalidIndexesCheck;
    @Autowired
    private DuplicatedIndexesCheckOnHost duplicatedIndexesCheck;
    @Autowired
    private IntersectedIndexesCheckOnHost intersectedIndexesCheck;
    @Autowired
    private ForeignKeysNotCoveredWithIndexCheckOnHost foreignKeysNotCoveredWithIndexCheck;
    @Autowired
    private TablesWithoutPrimaryKeyCheckOnHost tablesWithoutPrimaryKeyCheck;
    @Autowired
    private IndexesWithNullValuesCheckOnHost indexesWithNullValuesCheck;
    @Autowired
    private TablesWithoutDescriptionCheckOnHost tablesWithoutDescriptionCheck;
    @Autowired
    private ColumnsWithoutDescriptionCheckOnHost columnsWithoutDescriptionCheck;
    @Autowired
    private ColumnsWithJsonTypeCheckOnHost columnsWithJsonTypeCheck;
    @Autowired
    private ColumnsWithSerialTypesCheckOnHost columnsWithSerialTypesCheck;
    @Autowired
    private FunctionsWithoutDescriptionCheckOnHost functionsWithoutDescriptionCheck;
    @Autowired
    private IndexesWithBooleanCheckOnHost indexesWithBooleanCheck;
    @Autowired
    private NotValidConstraintsCheckOnHost notValidConstraintsCheck;
    @Autowired
    private BtreeIndexesOnArrayColumnsCheckOnHost btreeIndexesOnArrayColumnsCheck;
    @Autowired
    private SequenceOverflowCheckOnHost sequenceOverflowCheck;

    @Test
    @DisplayName("Always check PostgreSQL version in your tests")
    void checkPostgresVersion() {
        final String pgVersion = jdbcTemplate.queryForObject("select version();", String.class);
        assertThat(pgVersion)
                .startsWith("PostgreSQL 16.2");
    }

    @Test
    void getInvalidIndexesShouldReturnNothing() {
        assertThat(invalidIndexesCheck.check())
                .isEmpty();
    }

    @Test
    void getDuplicatedIndexesShouldReturnNothing() {
        assertThat(duplicatedIndexesCheck.check())
                .isEmpty();
    }

    @Test
    void getIntersectedIndexesShouldReturnNothing() {
        assertThat(intersectedIndexesCheck.check())
                .isEmpty();
    }

    @Test
    void getForeignKeysNotCoveredWithIndexShouldReturnNothing() {
        assertThat(foreignKeysNotCoveredWithIndexCheck.check())
                .isEmpty();
    }

    @Test
    void getTablesWithoutPrimaryKeyShouldReturnNothing() {
        assertThat(tablesWithoutPrimaryKeyCheck.check())
                .isEmpty();
    }

    @Test
    void getIndexesWithNullValuesShouldReturnNothing() {
        assertThat(indexesWithNullValuesCheck.check())
                .isEmpty();
    }

    @Test
    void getTablesWithoutDescriptionShouldReturnNothing() {
        assertThat(tablesWithoutDescriptionCheck.check())
                .isEmpty();
    }

    @Test
    void getColumnsWithoutDescriptionShouldReturnSeveralRows() {
        assertThat(columnsWithoutDescriptionCheck.check())
                .hasSize(4);
    }

    @Test
    void getColumnsWithJsonTypeShouldReturnNothing() {
        assertThat(columnsWithJsonTypeCheck.check())
                .isEmpty();
    }

    @Test
    void getColumnsWithSerialTypesShouldReturnNothing() {
        assertThat(columnsWithSerialTypesCheck.check())
                .isEmpty();
    }

    @Test
    void getFunctionsWithoutDescriptionShouldReturnNothing() {
        assertThat(functionsWithoutDescriptionCheck.check())
                .isEmpty();
    }

    @Test
    void indexesWithBooleanShouldReturnNothing() {
        assertThat(indexesWithBooleanCheck.check())
                .isEmpty();
    }

    @Test
    void notValidConstraintsShouldReturnNothing() {
        assertThat(notValidConstraintsCheck.check())
                .isEmpty();
    }

    @Test
    void btreeIndexesOnArrayColumnsShouldReturnNothing() {
        assertThat(btreeIndexesOnArrayColumnsCheck.check())
                .isEmpty();
    }

    @Test
    void sequenceOverflowCheckShouldReturnNothing() {
        assertThat(sequenceOverflowCheck.check())
                .isEmpty();
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

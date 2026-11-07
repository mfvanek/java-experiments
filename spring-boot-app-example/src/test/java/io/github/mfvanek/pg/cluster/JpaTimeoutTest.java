package io.github.mfvanek.pg.cluster;

import io.github.mfvanek.pg.cluster.support.BaseTest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class JpaTimeoutTest extends BaseTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void failsWhenQueryTooLong() {
        final Query query = entityManager
                .createNativeQuery(
                        "SELECT pg_sleep(1.1)");
        assertThatThrownBy(query::getResultList)
                .isInstanceOf(PersistenceException.class)
                .hasMessageContaining("ERROR: canceling statement due to user request")
                .rootCause()
                .isInstanceOfSatisfying(SQLException.class, e -> assertThat(e.getSQLState()).isEqualTo("57014")); // query_canceled
    }

    @Test
    @DisplayName("Does not throw exception when query does not exceed timeout")
    void exceptionWithNotLongQuery() {
        final Query query = entityManager
                .createNativeQuery(
                        "SELECT pg_sleep(0.9)");
        assertThatNoException().isThrownBy(query::getResultList);
    }
}

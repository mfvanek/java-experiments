package io.github.mfvanek.pg.cluster.controllers;

import io.github.mfvanek.pg.cluster.support.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext
class ClusterSupportControllerTest extends BaseTest {

    @Test
    void failoverCanBePerformedOnlyOnce() {
        final WebTestClient client = webTestClient.mutate()
                .responseTimeout(Duration.ofMinutes(5L))
                .build();

        final Boolean firstResult = client.post()
                .uri("/v1/cluster/failover")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class)
                .returnResult()
                .getResponseBody();
        assertThat(firstResult).isTrue();

        final Boolean secondResult = client.post()
                .uri("/v1/cluster/failover")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class)
                .returnResult()
                .getResponseBody();
        assertThat(secondResult).isFalse();
    }
}

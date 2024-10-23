package io.github.mfvanek.reactive.mongodb.example;

import io.github.mfvanek.reactive.mongodb.example.support.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.annotation.Nonnull;

import static org.assertj.core.api.Assertions.assertThat;

class ActuatorEndpointTest extends BaseTest {

    private WebTestClient actuatorClient;

    @BeforeEach
    void setUp() {
        this.actuatorClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + actuatorPort + "/actuator/")
                .build();
    }

    @Test
    void actuatorShouldBeRunOnSeparatePort() {
        assertThat(actuatorPort)
                .isNotEqualTo(port);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "health|{\"status\":\"UP\",\"groups\":[\"liveness\",\"readiness\"]}|application/json",
            "health/liveness|{\"status\":\"UP\"}|application/json",
            "health/readiness|{\"status\":\"UP\"}|application/json",
            "info|\"version\":|application/json"}, delimiter = '|')
    void actuatorEndpointShouldReturnOk(@Nonnull final String endpointName,
                                        @Nonnull final String expectedSubstring,
                                        @Nonnull final String mediaType) {
        final var result = actuatorClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(endpointName)
                        .build())
                .accept(MediaType.valueOf(mediaType))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        assertThat(result)
                .contains(expectedSubstring);
    }

    @Test
    void swaggerUiEndpointShouldReturnNotFound() {
        final var result = actuatorClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("swagger-ui")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        assertThat(result)
                .contains("\"status\":404,\"error\":\"Not Found\"")
                .contains("\"path\":\"/actuator/swagger-ui\"");
    }

    @Test
    void readinessProbeShouldBeCollectedFromApplicationMainPort() {
        final var result = webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("readyz")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        assertThat(result)
                .isEqualTo("{\"status\":\"UP\"}");

        final String metricsResult = actuatorClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("prometheus")
                        .build())
                .accept(MediaType.valueOf("text/plain"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        assertThat(metricsResult)
                .contains("http_server_requests_seconds_bucket");
    }
}

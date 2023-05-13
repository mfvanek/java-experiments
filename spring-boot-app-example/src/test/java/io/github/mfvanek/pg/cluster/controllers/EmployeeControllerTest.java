package io.github.mfvanek.pg.cluster.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.mfvanek.pg.cluster.dtos.EmployeeCreationRequest;
import io.github.mfvanek.pg.cluster.dtos.EmployeeDto;
import io.github.mfvanek.pg.cluster.support.BaseTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

class EmployeeControllerTest extends BaseTest {

    @Test
    void getEmployeeShouldReturnNotFoundForRandomId() {
        final var result = webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/employee/{id}")
                        .build(UUID.randomUUID()))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();
        assertThat(result)
                .isEqualTo(EmployeeDto.builder().build());
    }

    @Test
    void getAllShouldReturnEmptyListOnEmptyDatabase() {
        final var result = webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/employee/all")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeDto[].class)
                .returnResult()
                .getResponseBody();
        assertThat(result)
                .isEmpty();
    }

    @Test
    void createEmployee() {
        final var employeeCreationRequest = EmployeeCreationRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .standardHoursPerDay(8)
                .salaryPerHour(new BigDecimal("400.12"))
                .build();

        final var result = webTestClient.post()
                .uri("/v1/employee")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(employeeCreationRequest)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EmployeeDto.class)
                .returnResult();

        assertThat(result)
                .isNotNull();
        assertThat(result.getResponseHeaders())
                .hasSizeGreaterThanOrEqualTo(2)
                .containsKeys(HttpHeaders.CONTENT_TYPE, HttpHeaders.LOCATION);

        final List<String> location = result.getResponseHeaders().get(HttpHeaders.LOCATION);
        assertThat(location)
                .isNotNull()
                .hasSize(1)
                .first(InstanceOfAssertFactories.STRING)
                .startsWith("http://localhost:%d/api/v1/employee/".formatted(port));

        final var createdEmployee = result.getResponseBody();
        assertThat(createdEmployee)
                .isNotNull()
                .satisfies(e -> {
                    assertThat(e.getId())
                            .isNotNull();
                    assertThat(e.getCreatedAt())
                            .isEqualTo(LocalDateTime.now(clock));
                    assertThat(e.getUpdatedAt())
                            .isNull();
                    assertThat(e.getFirstName())
                            .isEqualTo("John");
                    assertThat(e.getLastName())
                            .isEqualTo("Doe");
                    assertThat(e.getStandardHoursPerDay())
                            .isEqualTo(8);
                    assertThat(e.getSalaryPerHour())
                            .isEqualByComparingTo("400.12");
                });

        final var foundEmployee = webTestClient.get()
                .uri(location.get(0))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(foundEmployee)
                .isNotNull()
                .satisfies(e -> assertThat(e.getId())
                        .isEqualTo(createdEmployee.getId()));

        final var allEmployees = webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/employee/all")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeDto[].class)
                .returnResult()
                .getResponseBody();
        assertThat(allEmployees)
                .hasSize(1)
                .containsExactly(createdEmployee);
    }
}

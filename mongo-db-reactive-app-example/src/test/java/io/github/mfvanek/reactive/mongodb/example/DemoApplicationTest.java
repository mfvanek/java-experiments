package io.github.mfvanek.reactive.mongodb.example;

import io.github.mfvanek.reactive.mongodb.example.model.Task;
import io.github.mfvanek.reactive.mongodb.example.model.User;
import io.github.mfvanek.reactive.mongodb.example.repository.TaskRepository;
import io.github.mfvanek.reactive.mongodb.example.repository.UserRepository;
import io.github.mfvanek.reactive.mongodb.example.support.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class DemoApplicationTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private Clock clock;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        assertThat(countDocumentsInCollection("users"))
                .isZero();

        final User user = User.of("test_user", "test_user@example.org");
        final User savedUser = userRepository.save(user)
                .block();

        assertThat(countDocumentsInCollection("users"))
                .isOne();
        assertThat(userRepository.findAll().collectList().block())
                .hasSize(1)
                .first()
                .isEqualTo(savedUser)
                .isEqualTo(user)
                .isEqualTo(new User(user.id(), "test_user", "test_user@example.org"));

        final Task task = Task.of(user.id(), Instant.now(clock));
        final Task savedTask = taskRepository.save(task).block();
        assertThat(countDocumentsInCollection("tasks"))
                .isOne();
        assertThat(taskRepository.findAll().collectList().block())
                .hasSize(1)
                .first()
                .usingRecursiveComparison()
                .ignoringFields("createdAt") // due to truncation
                .isEqualTo(savedTask)
                .isEqualTo(task);

        final User foundUser = webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/user/{id}").build(user.id()))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();
        assertThat(foundUser)
                .isEqualTo(user);
    }

    private long countDocumentsInCollection(final String collectionName) {
        return Objects.requireNonNullElse(mongoTemplate.getCollection(collectionName)
                .flatMap(c -> Mono.from(c.countDocuments()))
                .block(), 0L);
    }
}

package io.github.mfvanek.reactive.mongodb.example;

import io.github.mfvanek.reactive.mongodb.example.model.User;
import io.github.mfvanek.reactive.mongodb.example.repository.UserRepository;
import io.github.mfvanek.reactive.mongodb.example.support.BaseTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class DemoApplicationTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        assertThat(countDocumentsInCollection("users"))
                .isZero();

        userRepository.save(new User(new ObjectId(), "test_user", "test_user@example.org"))
                .block();

        assertThat(countDocumentsInCollection("users"))
                .isOne();
    }

    private long countDocumentsInCollection(final String collectionName) {
        return Objects.requireNonNullElse(mongoTemplate.getCollection(collectionName)
                .flatMap(c -> Mono.from(c.countDocuments()))
                .block(), 0L);
    }
}

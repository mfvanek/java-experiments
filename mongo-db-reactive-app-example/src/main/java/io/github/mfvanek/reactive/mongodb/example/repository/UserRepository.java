package io.github.mfvanek.reactive.mongodb.example.repository;

import io.github.mfvanek.reactive.mongodb.example.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

}

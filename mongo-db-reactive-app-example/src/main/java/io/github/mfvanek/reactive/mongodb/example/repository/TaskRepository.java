package io.github.mfvanek.reactive.mongodb.example.repository;

import io.github.mfvanek.reactive.mongodb.example.model.Task;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends ReactiveMongoRepository<Task, String> {

}

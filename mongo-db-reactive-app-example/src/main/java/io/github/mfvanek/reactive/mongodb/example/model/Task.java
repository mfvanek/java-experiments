package io.github.mfvanek.reactive.mongodb.example.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "tasks")
public record Task(@Id String id, String authorId, Instant createdAt) {

    public static Task of(String authorId, Instant createdAt) {
        return new Task(ObjectId.get().toHexString(), authorId, createdAt);
    }
}

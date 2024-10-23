package io.github.mfvanek.reactive.mongodb.example.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public record User(ObjectId id, String userName, String email) {
}

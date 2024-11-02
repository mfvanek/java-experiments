package io.github.mfvanek.reactive.mongodb.example.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public record User(@Id String id, String userName, String email) {

    public static User of(String userName, String email) {
        return new User(ObjectId.get().toHexString(), userName, email);
    }
}
